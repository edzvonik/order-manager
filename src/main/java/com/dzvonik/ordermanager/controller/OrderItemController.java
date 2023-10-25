package com.dzvonik.ordermanager.controller;

import com.dzvonik.ordermanager.model.dto.OrderItemResponse;
import com.dzvonik.ordermanager.model.dto.UpdateOrderItemRequest;
import com.dzvonik.ordermanager.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Operation(summary = "Get All By Order ID", description = "Retrieve order items detail by order unique identifier.")
    public ResponseEntity<List<OrderItemResponse>> getAllByOrder(@PathVariable Long orderId) {
        List<OrderItemResponse> orderItemsResponse = orderItemService.getAllByOrderId(orderId);
        return ResponseEntity.ok(orderItemsResponse);
    }

    @PatchMapping(value = "/{itemId}")
    @Operation(summary = "Update by ID", description = "Update an order item using its unique identifier.")
    public ResponseEntity<OrderItemResponse> updateById(
            @PathVariable Long orderId,
            @PathVariable Long itemId,
            @RequestBody UpdateOrderItemRequest updateOrderItemRequest) {
        OrderItemResponse updatedOrderItem = orderItemService.updateById(itemId, updateOrderItemRequest);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping(value = "/{itemId}")
    @Operation(summary = "Delete by ID", description = "Delete an order item by its unique identifier.")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        orderItemService.deleteById(itemId);
        return ResponseEntity.ok().build();
    }

}
