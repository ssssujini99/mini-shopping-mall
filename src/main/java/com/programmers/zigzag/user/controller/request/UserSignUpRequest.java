package com.programmers.zigzag.user.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSignUpRequest {

    @NotNull(message = "email은 null일 수 없습니다.")
    @Email(message = "이메일 형식을 갖추어야 합니다.")
    private String email;

    @NotNull(message = "nickname은 null일 수 없습니다.")
    private String nickname;

    @NotNull(message = "password는 null일 수 없습니다.")
    private String password;

    @Pattern(regexp = "^(customer|seller)$", message = "userType은 반드시 'customer' 나 'seller' 둘 중 하나이어야 합니다.")
    private String userType;

}
