package com.programmers.zigzag.auth.interceptor;

import com.programmers.zigzag.auth.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        tokenProvider.throwIfTokenIsNull(token);
        String accessToken = tokenProvider.throwIfNotValidToken(token);

        long userId = tokenProvider.getUserId(accessToken);
        request.setAttribute("userId", userId);
        return true;
    }

}
