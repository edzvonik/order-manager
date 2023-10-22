package com.dzvonik.ordermanager.service;

import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderResponse;

public interface OrderService {

    public OrderResponse createOrder(CreateOrderRequest createOrderRequest);

}
