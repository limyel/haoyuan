package com.limyel.haoyuan.common.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("用户未登录访问受保护资源: ", authException);
        if (authException instanceof InsufficientAuthenticationException) {
            // todo
//            RespUtil.writeResp(response, R.failed(new AuthFailureErrorCode("Unloggin", "用户未登录")));
            return;
        }

        // todo
//        RespUtil.writeResp(response, R.failed(new AuthFailureErrorCode(null, authException.getMessage())));
    }
}
