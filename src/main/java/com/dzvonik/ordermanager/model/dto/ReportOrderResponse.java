package com.dzvonik.ordermanager.model.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReportOrderResponse extends RepresentationModel<ReportOrderResponse> {

    private Long id;
    private String date;
    private List<OrderItemResponse> items;
    private BigDecimal price;

}

