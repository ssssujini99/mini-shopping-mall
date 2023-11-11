package com.programmers.zigzag.user.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignUpRequest {

    private String email;

    private String nickname;

    private String password;

    private String userType;

}
