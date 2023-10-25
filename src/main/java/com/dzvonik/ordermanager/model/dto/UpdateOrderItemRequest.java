package com.dzvonik.ordermanager.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateOrderItemRequest {

    @Min(0)
    @NotBlank(message = "Quantity is required.")
    private int quantity;

}
