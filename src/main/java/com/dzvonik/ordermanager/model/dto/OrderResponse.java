package com.dzvonik.ordermanager.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {

    private Long id;
    private String date;
    private List<OrderItemResponse> orderItems;
    private BigDecimal orderPrice;

}

