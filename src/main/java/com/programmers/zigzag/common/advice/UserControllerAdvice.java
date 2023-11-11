package com.programmers.zigzag.common.advice;

import com.programmers.zigzag.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorTemplate handleUserNotFoundException(RuntimeException runtimeException) {
        return ErrorTemplate.of(runtimeException.getMessage());
    }
}
