package com.programmers.zigzag.user.application;

import com.programmers.zigzag.auth.token.TokenProvider;
import com.programmers.zigzag.user.application.dto.UserDto;
import com.programmers.zigzag.user.application.mapper.Mapper;
import com.programmers.zigzag.user.controller.request.UserSignUpRequest;
import com.programmers.zigzag.user.controller.response.UserSignUpResponse;
import com.programmers.zigzag.user.domain.User;
import com.programmers.zigzag.user.domain.UserRole;
import com.programmers.zigzag.user.exception.UserNotFoundException;
import com.programmers.zigzag.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest) {

        User user = new User(userSignUpRequest.getEmail(), userSignUpRequest.getNickname(), userSignUpRequest.getPassword(), UserRole.getType(userSignUpRequest.getUserType()));
        User savedUser = userRepository.save(user);
        String accessToken = tokenProvider.generateToken(savedUser);
        return new UserSignUpResponse(accessToken);
    }

    @Transactional(readOnly = true)
    public UserDto getUser(long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        User user = optionalUser.get();
        return Mapper.toUserDto(user);
    }

}
