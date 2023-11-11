package com.programmers.zigzag.auth.token;

import com.programmers.zigzag.user.domain.User;

public interface TokenProvider {

    String generateToken(User user);
    void throwIfTokenIsNull(String token);
    String throwIfNotValidToken(String token);
    long getUserId(String token);
    String getUserRole(String token);
}
