package com.programmers.zigzag.user.application.dto;

import com.programmers.zigzag.user.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private String nickname;

    private UserRole userRole;
}
