package com.programmers.zigzag.user.controller.response;

import com.programmers.zigzag.user.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private final Long id;

    private final String email;

    private final String nickname;

    private final UserRole userRole;
}
