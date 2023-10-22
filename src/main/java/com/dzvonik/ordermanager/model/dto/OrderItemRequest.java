package com.dzvonik.ordermanager.model.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long productId;
    private int quantity;

}
