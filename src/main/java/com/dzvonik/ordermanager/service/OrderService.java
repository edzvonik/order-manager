package com.dzvonik.ordermanager.service;

import com.dzvonik.ordermanager.model.dto.CreateOrderRequest;
import com.dzvonik.ordermanager.model.dto.OrderResponse;
import com.dzvonik.ordermanager.model.dto.PatchOperation;
import com.dzvonik.ordermanager.model.dto.PeriodRequest;
import com.dzvonik.ordermanager.model.dto.ReportOrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse create(CreateOrderRequest createOrderRequest);
    OrderResponse getById(Long orderId);
    List<OrderResponse> getAll();
    List<ReportOrderResponse> getReportByPeriod(PeriodRequest periodRequest);
    void delete(Long orderId);
    // OrderResponse update(List<PatchOperation> patchOperations);

}
