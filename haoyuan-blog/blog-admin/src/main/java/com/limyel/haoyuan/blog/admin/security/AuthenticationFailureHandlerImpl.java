package com.limyel.haoyuan.blog.admin.security;

import com.limyel.haoyuan.common.core.exception.auth.BadTokenException;
import com.limyel.haoyuan.common.core.exception.code.AuthFailureErrorCode;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.common.web.util.RespUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("AuthenticationException: ", exception);
        if (exception instanceof UsernameOrPasswordNullException) {
            RespUtil.writeResp(response, R.failed(UsernameOrPasswordNullException.ERROR_CODE));
        } else if (exception instanceof BadCredentialsException) {
            RespUtil.writeResp(response, R.failed(BadTokenException.ERROR_CODE));
        } else {
            // todo
            RespUtil.writeResp(response, R.failed(new AuthFailureErrorCode("LoginFailed", "登录失败")));
        }
    }

}
