package com.programmers.zigzag.auth.exception;

public class AuthInvalidException extends RuntimeException {

    public AuthInvalidException() {
        super("인증 권한이 없습니다.");
    }
}
