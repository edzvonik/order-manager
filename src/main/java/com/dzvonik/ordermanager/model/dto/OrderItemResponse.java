package com.dzvonik.ordermanager.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {

    private Long id;
    private ProductResponse product;
    private int quantity;
    private BigDecimal orderItemPrice;

}
