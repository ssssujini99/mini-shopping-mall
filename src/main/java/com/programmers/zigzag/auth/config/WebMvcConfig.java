package com.programmers.zigzag.auth.config;

import com.programmers.zigzag.auth.interceptor.AuthorityInterceptor;
import com.programmers.zigzag.auth.interceptor.TokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;
    private final AuthorityInterceptor authorityInterceptor;

    private static final String[] JWT_INTERCEPTOR_URLS = {
            "/auth/info",
    };

    private static final String[] AUTH_INTERCEPTOR_URLS = {
            ""
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns(JWT_INTERCEPTOR_URLS);
        registry.addInterceptor(authorityInterceptor).addPathPatterns(AUTH_INTERCEPTOR_URLS);
    }


}
