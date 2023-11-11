package com.programmers.zigzag.auth.exception;

public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException() {
        super("토큰이 유효하지 않습니다. 다시 로그인을 해주세요.");
    }
}
