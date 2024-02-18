package com.limyel.haoyuan.module.system.security.handler;

import com.limyel.haoyuan.common.exception.GlobalErrorCode;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.framework.web.util.JsonUtil;
import com.limyel.haoyuan.framework.web.util.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败异常处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Result<Object> result = new Result<>(GlobalErrorCode.UNAUTHORIZED);
        WebUtil.writeResp(response, JsonUtil.encode(result));
    }

}
