package com.dzvonik.ordermanager.service.impl;

import com.dzvonik.ordermanager.exception.ProductNotFoundException;
import com.dzvonik.ordermanager.model.Order;
import com.dzvonik.ordermanager.model.OrderItem;
import com.dzvonik.ordermanager.model.Product;
import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderItemRequest;
import com.dzvonik.ordermanager.model.dto.OrderResponse;
import com.dzvonik.ordermanager.repository.OrderItemRepository;
import com.dzvonik.ordermanager.repository.OrderRepository;
import com.dzvonik.ordermanager.repository.ProductRepository;
import com.dzvonik.ordermanager.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        Order newOrder = mapper.map(createOrderRequest, Order.class);
        BigDecimal totalOrderPrice = BigDecimal.ZERO;

        newOrder = orderRepository.save(newOrder);

        for (OrderItemRequest orderItemRequest : createOrderRequest.getItems()) {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(orderItemRequest.getProductId()));

            OrderItem newItem = OrderItem.builder()
                    .order(newOrder)
                    .product(product)
                    .quantity(orderItemRequest.getQuantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity())))
                    .build();

            totalOrderPrice = totalOrderPrice.add(newItem.getTotalPrice());

            orderItemRepository.save(newItem);
        }

        OrderResponse orderResponse = mapper.map(newOrder, OrderResponse.class);
        orderResponse.setTotalPrice(totalOrderPrice);
        return orderResponse;
    }

}
