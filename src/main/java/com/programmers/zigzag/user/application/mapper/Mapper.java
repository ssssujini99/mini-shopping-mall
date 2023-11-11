package com.programmers.zigzag.user.application.mapper;

import com.programmers.zigzag.user.application.dto.UserDto;
import com.programmers.zigzag.user.domain.User;

public final class Mapper {

    private Mapper() {
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getNickname(), user.getUserRole());
    }
}
