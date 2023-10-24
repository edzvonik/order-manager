package com.dzvonik.ordermanager.service.impl;

import com.dzvonik.ordermanager.exception.ResourceNotFoundException;
import com.dzvonik.ordermanager.model.Order;
import com.dzvonik.ordermanager.model.OrderItem;
import com.dzvonik.ordermanager.model.Product;
import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderItemRequest;
import com.dzvonik.ordermanager.model.dto.OrderItemResponse;
import com.dzvonik.ordermanager.model.dto.OrderResponse;
import com.dzvonik.ordermanager.model.dto.OrdersByPeriodRequest;
import com.dzvonik.ordermanager.repository.OrderItemRepository;
import com.dzvonik.ordermanager.repository.OrderRepository;
import com.dzvonik.ordermanager.repository.ProductRepository;
import com.dzvonik.ordermanager.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        Order newOrder = mapper.map(createOrderRequest, Order.class);
        newOrder = orderRepository.save(newOrder);
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : createOrderRequest.getItems()) {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + orderItemRequest.getProductId()));

            OrderItem newItem = new OrderItem(
                    newOrder,
                    product,
                    orderItemRequest.getQuantity(),
                    product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()))
            );
            orderItems.add(newItem);
        }

        orderItemRepository.saveAll(orderItems);

        OrderResponse orderResponse = mapper.map(newOrder, OrderResponse.class);
        orderResponse.setOrderItems(orderItems.stream()
                .map(orderItem -> mapper.map(orderItem, OrderItemResponse.class))
                .collect(Collectors.toList()));
        orderResponse.setOrderPrice(calculateOrderPrice(orderItems));

        return orderResponse;
    }

    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);

        OrderResponse orderResponse = mapper.map(order, OrderResponse.class);
        orderResponse.setOrderItems(orderItems.stream()
                .map(orderItem -> mapper.map(orderItem, OrderItemResponse.class))
                .collect(Collectors.toList()));
        orderResponse.setOrderPrice(calculateOrderPrice(orderItems));

        return orderResponse;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return convertOrdersToOrderResponses(orders);
    }

    public List<OrderResponse> getAllOrdersByPeriod(OrdersByPeriodRequest ordersByPeriodRequest) {
        LocalDate start = mapper.map(ordersByPeriodRequest.getStart(), LocalDate.class);
        LocalDate end = mapper.map(ordersByPeriodRequest.getEnd(), LocalDate.class);
        List<Order> ordersByPeriod = orderRepository.findAllByDateBetweenOrderByDateAsc(start, end);

        return convertOrdersToOrderResponses(ordersByPeriod);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        orderItemRepository.removeAllByOrder(order);
        orderRepository.deleteById(orderId);
    }

    private BigDecimal calculateOrderPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getOrderItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderResponse> convertOrdersToOrderResponses(List<Order> orders) {
        return orders.stream()
                .map(order -> {
                    OrderResponse orderResponse = mapper.map(order, OrderResponse.class);
                    List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);

                    List<OrderItemResponse> orderItemResponses = orderItems.stream()
                            .map(orderItem -> mapper.map(orderItem, OrderItemResponse.class))
                            .collect(Collectors.toList());

                    orderResponse.setOrderItems(orderItemResponses);
                    orderResponse.setOrderPrice(calculateOrderPrice(orderItems));

                    return orderResponse;
                })
                .collect(Collectors.toList());
    }

}
