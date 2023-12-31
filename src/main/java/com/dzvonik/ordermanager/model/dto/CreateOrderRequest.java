package com.dzvonik.ordermanager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Date is required.")
    @Schema(example = "2023-10-20")
    private String date;

    @NotEmpty(message = "Items is required.")
    private List<OrderItemRequest> items;

}

