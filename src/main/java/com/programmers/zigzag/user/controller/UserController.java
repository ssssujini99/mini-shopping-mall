package com.programmers.zigzag.user.controller;

import com.programmers.zigzag.user.application.UserService;
import com.programmers.zigzag.user.application.dto.UserDto;
import com.programmers.zigzag.user.controller.request.UserSignUpRequest;
import com.programmers.zigzag.user.controller.response.UserInfoResponse;
import com.programmers.zigzag.user.controller.response.UserSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/signUp")
    public ResponseEntity<UserSignUpResponse> signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        UserSignUpResponse userSignUpResponse = userService.createUser(userSignUpRequest);
        return ResponseEntity.ok(userSignUpResponse);
    }

    @PostMapping("/auth/info")
    public ResponseEntity<UserInfoResponse> getInfo(@RequestAttribute Long userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(getUserInfoResponse(userDto));
    }

    private static UserInfoResponse getUserInfoResponse(UserDto userDto) {
        return new UserInfoResponse(userDto.getId(), userDto.getEmail(), userDto.getNickname(), userDto.getUserRole());
    }

}
