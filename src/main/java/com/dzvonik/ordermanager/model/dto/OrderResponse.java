package com.dzvonik.ordermanager.model.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class OrderResponse extends RepresentationModel<OrderResponse> {

    private Long id;
    private String date;

}

