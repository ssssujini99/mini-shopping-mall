package com.programmers.zigzag.auth.interceptor;

import com.programmers.zigzag.auth.exception.AuthInvalidException;
import com.programmers.zigzag.auth.token.TokenProvider;
import com.programmers.zigzag.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        String accessToken = tokenProvider.throwIfNotValidToken(token);
        String userRole = tokenProvider.getUserRole(accessToken);

        if (UserRole.getType(userRole) != UserRole.SELLER) {
            throw new AuthInvalidException();
        }

        request.setAttribute("userRole", userRole);
        return true;
    }
}
