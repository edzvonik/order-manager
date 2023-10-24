package com.dzvonik.ordermanager.model.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResourceExceptionResponse {

    private HttpStatus status;
    private String message;

}
