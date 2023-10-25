package com.dzvonik.ordermanager.service.impl;

import com.dzvonik.ordermanager.controller.OrderItemController;
import com.dzvonik.ordermanager.exception.ResourceNotFoundException;
import com.dzvonik.ordermanager.model.Order;
import com.dzvonik.ordermanager.model.OrderItem;
import com.dzvonik.ordermanager.model.Product;
import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderItemRequest;
import com.dzvonik.ordermanager.model.dto.OrderItemResponse;
import com.dzvonik.ordermanager.model.dto.OrderResponse;
import com.dzvonik.ordermanager.model.dto.PeriodRequest;
import com.dzvonik.ordermanager.model.dto.ReportOrderResponse;
import com.dzvonik.ordermanager.repository.OrderItemRepository;
import com.dzvonik.ordermanager.repository.OrderRepository;
import com.dzvonik.ordermanager.repository.ProductRepository;
import com.dzvonik.ordermanager.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderResponse create(CreateOrderRequest createOrderRequest) {
        Order newOrder = mapper.map(createOrderRequest, Order.class);
        newOrder = orderRepository.save(newOrder);
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : createOrderRequest.getItems()) {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + orderItemRequest.getProductId()));

            OrderItem newItem = new OrderItem(
                    newOrder,
                    product,
                    orderItemRequest.getQuantity()
            );

            orderItems.add(newItem);
        }

        orderItemRepository.saveAll(orderItems);
        // newOrder = orderRepository.save(newOrder);

        OrderResponse orderResponse = mapper.map(newOrder, OrderResponse.class);
        Link orderItemsLink = linkTo(methodOn(OrderItemController.class).getOrderItemsByOrder(newOrder.getId())).withRel("orderItems");
        orderResponse.add(orderItemsLink);
        return orderResponse;
    }

    public OrderResponse getById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        OrderResponse orderResponse = mapper.map(order, OrderResponse.class);
        Link orderItemsLink = linkTo(methodOn(OrderItemController.class).getOrderItemsByOrder(orderId)).withRel("orderItems");
        orderResponse.add(orderItemsLink);
        return orderResponse;
    }

    public List<OrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();
        return convertOrdersToOrderResponses(orders);
    }

    public List<ReportOrderResponse> getReportByPeriod(PeriodRequest periodRequest) {
        LocalDate start = mapper.map(periodRequest.getStart(), LocalDate.class);
        LocalDate end = mapper.map(periodRequest.getEnd(), LocalDate.class);
        List<Order> ordersByPeriod = orderRepository.findAllByDateBetweenOrderByDateAsc(start, end);

        return ordersByPeriod.stream()
                .map(order -> {
                    ReportOrderResponse reportOrderResponse = mapper.map(order, ReportOrderResponse.class);
                    List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);

                    List<OrderItemResponse> orderItemResponses = orderItems.stream()
                            .map(orderItem -> mapper.map(orderItem, OrderItemResponse.class))
                            .collect(Collectors.toList());

                    reportOrderResponse.setItems(orderItemResponses);
                    reportOrderResponse.setPrice(order.calculateOrderPrice());

                    return reportOrderResponse;
                })
                .collect(Collectors.toList());
    }

    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }

//    public OrderResponse update(List<PatchOperation> patchOperations) {
//
//    }

    private BigDecimal calculateOrderPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getOrderItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderResponse> convertOrdersToOrderResponses(List<Order> orders) {
        return orders.stream()
                .map(order -> {
                    OrderResponse orderResponse = mapper.map(order, OrderResponse.class);
                    Link orderItemsLink = linkTo(methodOn(OrderItemController.class).getOrderItemsByOrder(order.getId())).withRel("orderItems");
                    orderResponse.add(orderItemsLink);
                    return orderResponse;
                })
                .collect(Collectors.toList());
    }

}
