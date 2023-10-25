package com.dzvonik.ordermanager.controller;

import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderResponse;
import com.dzvonik.ordermanager.model.dto.PeriodRequest;
import com.dzvonik.ordermanager.model.dto.ReportOrderResponse;
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
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "Allows managing orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    @Operation(summary = "Create a New Order", description = "Create a new order with the provided details.")
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderResponse createdOrder = orderService.create(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get by ID", description = "Retrieve order details by its unique identifier.")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long orderId) {
        OrderResponse order = orderService.getById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    @Operation(summary = "Get All", description = "Retrieve a list of all orders.")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/report")
    @Operation(summary = "Get Report By Period", description = "Retrieve a list of all orders with detail by period.")
    public ResponseEntity<List<ReportOrderResponse>> getAllByPeriod(@Valid PeriodRequest periodRequest) {
        List<ReportOrderResponse> reportByPeriod = orderService.getReportByPeriod(periodRequest);
        return ResponseEntity.ok(reportByPeriod);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Delete by ID", description = "Delete an order by its unique identifier.")
    public ResponseEntity<Void> delete(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.ok().build();
    }

}
