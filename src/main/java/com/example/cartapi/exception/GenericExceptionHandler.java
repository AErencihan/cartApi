package com.example.cartapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<?> handleException(GlobalException exception){
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", exception.getMessage());

        return ResponseEntity
                .status(exception.getHttpStatus() != null ? exception.getHttpStatus() : HttpStatus.BAD_GATEWAY)
                .body(errors);


    }
}
