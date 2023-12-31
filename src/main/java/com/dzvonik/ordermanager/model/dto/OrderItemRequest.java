package com.dzvonik.ordermanager.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderItemRequest {

    @Min(0)
    @NotBlank(message = "ID is required.")
    private Long productId;

    @Min(0)
    @NotBlank(message = "Quantity is required.")
    private int quantity;

}
