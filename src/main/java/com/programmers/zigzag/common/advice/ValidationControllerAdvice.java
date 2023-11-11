package com.programmers.zigzag.common.advice;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * javax.validation 어노테이션을 처리합니다.
 */
@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ValidationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorTemplate handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {

        String defaultMessage = methodArgumentNotValidException
                .getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return ErrorTemplate.of(defaultMessage);
    }
}
