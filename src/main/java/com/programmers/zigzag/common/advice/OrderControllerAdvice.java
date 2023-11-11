package com.programmers.zigzag.common.advice;

import com.programmers.zigzag.order.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorTemplate handleOrderNotFoundException(RuntimeException runtimeException) {
        return ErrorTemplate.of(runtimeException.getMessage());
    }
}
