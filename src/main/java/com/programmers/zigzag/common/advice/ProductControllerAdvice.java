package com.programmers.zigzag.common.advice;

import com.programmers.zigzag.product.exception.ProductNotFoundException;
import com.programmers.zigzag.product.exception.ProductStockQuantityInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductStockQuantityInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorTemplate handleProductStockQuantityInvalidException(RuntimeException runtimeException) {
        return ErrorTemplate.of(runtimeException.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorTemplate handleProductNotFoundException(RuntimeException runtimeException) {
        return ErrorTemplate.of(runtimeException.getMessage());
    }
}
