package com.example.cartapi.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;


}
