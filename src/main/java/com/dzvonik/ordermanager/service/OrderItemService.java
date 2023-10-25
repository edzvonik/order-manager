package com.dzvonik.ordermanager.service;

import com.dzvonik.ordermanager.model.dto.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getOrderItemsByOrderId(Long id);

}
