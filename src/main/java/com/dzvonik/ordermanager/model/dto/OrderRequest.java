package com.dzvonik.ordermanager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequest {

    @Min(0)
    @NotBlank
    private Long id;

    @Schema(example = "2023-10-20")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Date is required.")
    private String date;

}
