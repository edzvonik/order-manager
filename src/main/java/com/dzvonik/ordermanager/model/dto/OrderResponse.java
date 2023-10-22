package com.dzvonik.ordermanager.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {

    private Long id;
    private String date;
    private BigDecimal totalPrice;

}

