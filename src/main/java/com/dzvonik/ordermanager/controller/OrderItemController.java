package com.dzvonik.ordermanager.controller;

import com.dzvonik.ordermanager.model.dto.OrderItemResponse;
import com.dzvonik.ordermanager.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders/{orderId}/items")
@Tag(name = "Order Items", description = "Allows managing order items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    @Operation(summary = "Get Order Items", description = "Retrieve order items detail by order unique identifier.")
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrder(@PathVariable Long orderId) {
        List<OrderItemResponse> orderItemsResponse = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItemsResponse);
    }

}
