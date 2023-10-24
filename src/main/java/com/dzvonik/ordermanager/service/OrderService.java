package com.dzvonik.ordermanager.service;

import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderResponse;
import com.dzvonik.ordermanager.model.dto.OrdersByPeriodRequest;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest createOrderRequest);
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getAllOrdersByPeriod(OrdersByPeriodRequest ordersByPeriodRequest);
    void deleteOrder(Long orderId);

}
