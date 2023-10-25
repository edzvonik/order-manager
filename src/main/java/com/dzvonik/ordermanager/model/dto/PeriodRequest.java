package com.dzvonik.ordermanager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PeriodRequest {

    @Schema(example = "2023-10-20")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Start date is required.")
    private String start;

    @Schema(example = "2023-10-20")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "End date is required.")
    private String end;

}
