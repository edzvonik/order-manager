package com.dzvonik.ordermanager.exception;

import com.dzvonik.ordermanager.model.dto.ResourceExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResourceExceptionResponse exceptionResponse = new ResourceExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

}
