package com.dzvonik.ordermanager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrdersByPeriodRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "[Orders] Start date is required.")
    private String start;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "[Orders] End date is required.")
    private String end;

}
