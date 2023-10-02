package com.development.productService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RuntimeException{
    private HttpStatus errorCode;
    private String message;

    public NotFoundException(String message) {
        super(message);
    }
}
