package com.dzvonik.ordermanager.controller;

import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderResponse;
import com.dzvonik.ordermanager.model.dto.OrdersByPeriodRequest;
import com.dzvonik.ordermanager.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Allows managing orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    @Operation(summary = "Create a New Order", description = "Create a new order with the provided details.")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderResponse createdOrder = orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get Order by ID", description = "Retrieve order details by its unique identifier.")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        OrderResponse order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    @Operation(summary = "Get All Orders", description = "Retrieve a list of all orders in the system.")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/period")
    @Operation(summary = "Get All Orders By Period", description = "Retrieve a list of all orders by period in the system.")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByPeriod(@Valid OrdersByPeriodRequest ordersByPeriodRequest) {
        List<OrderResponse> ordersByPeriod = orderService.getAllOrdersByPeriod(ordersByPeriodRequest);
        return ResponseEntity.ok(ordersByPeriod);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Delete Order by ID", description = "Delete an order from the system by its unique identifier.")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
