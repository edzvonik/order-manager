package com.dzvonik.ordermanager.service.impl;

import com.dzvonik.ordermanager.exception.ResourceNotFoundException;
import com.dzvonik.ordermanager.model.Order;
import com.dzvonik.ordermanager.model.OrderItem;
import com.dzvonik.ordermanager.model.dto.OrderItemResponse;
import com.dzvonik.ordermanager.model.dto.UpdateOrderItemRequest;
import com.dzvonik.ordermanager.repository.OrderItemRepository;
import com.dzvonik.ordermanager.repository.OrderRepository;
import com.dzvonik.ordermanager.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final ModelMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemResponse> getAllByOrderId(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        List<OrderItem> orderItems = order.getOrderItems();
        return orderItems.stream()
                .map(orderItem -> mapper.map(orderItem, OrderItemResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponse updateById(Long id, UpdateOrderItemRequest updateOrderItemRequest) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with ID: " + id));

        orderItem.changeQuantity(updateOrderItemRequest.getQuantity());
        orderItem = orderItemRepository.save(orderItem);
        return mapper.map(orderItem, OrderItemResponse.class);
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

}
