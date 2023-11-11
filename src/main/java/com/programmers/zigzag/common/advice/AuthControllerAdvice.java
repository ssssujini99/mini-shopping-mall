package com.programmers.zigzag.common.advice;

import com.programmers.zigzag.auth.exception.AuthInvalidException;
import com.programmers.zigzag.auth.exception.TokenInvalidException;
import com.programmers.zigzag.auth.exception.TokenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ErrorTemplate handleAuthInvalidException(RuntimeException runtimeException) {
        return ErrorTemplate.of(runtimeException.getMessage());
    }

    @ExceptionHandler({TokenInvalidException.class, TokenNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorTemplate handleTokenException(RuntimeException runtimeException) {
        return ErrorTemplate.of(runtimeException.getMessage());
    }

}
