package com.dzvonik.ordermanager.model.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
public class OrderItemResponse extends RepresentationModel<OrderResponse> {

    private Long id;
    private ProductResponse product;
    private int quantity;
    private BigDecimal orderItemPrice;

}
