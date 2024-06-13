package com.limyel.haoyuan.common.security.handler;

import com.limyel.haoyuan.common.core.exception.code.AuthFailureErrorCode;
import com.limyel.haoyuan.common.core.exception.code.GlobalErrorCode;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.common.web.util.RespUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("登录成功访问受保护的资源，但是权限不够: ", accessDeniedException);
        RespUtil.writeResp(response, R.failed(AuthFailureErrorCode.FORBIDDEN));
    }
}
