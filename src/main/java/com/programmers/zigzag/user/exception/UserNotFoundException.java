package com.programmers.zigzag.user.exception;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException {

    @Getter
    private final long userId;

    public UserNotFoundException(long userId) {
        super("userId " + userId + " 에 해당하는 User가 존재하지 않습니다.");
        this.userId = userId;
    }
}
