package com.dzvonik.ordermanager.service;

import com.dzvonik.ordermanager.model.dto.OrderItemResponse;
import com.dzvonik.ordermanager.model.dto.UpdateOrderItemRequest;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getAllByOrderId(Long id);
    OrderItemResponse updateById(Long id, UpdateOrderItemRequest updateOrderItemRequest);
    void deleteById(Long id);

}
