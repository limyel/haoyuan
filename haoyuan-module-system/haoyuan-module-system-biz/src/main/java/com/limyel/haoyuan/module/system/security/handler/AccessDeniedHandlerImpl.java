package com.limyel.haoyuan.module.system.security.handler;

import com.limyel.haoyuan.common.exception.GlobalErrorCode;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.framework.web.util.JsonUtil;
import com.limyel.haoyuan.framework.web.util.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        Result<Object> result = new Result<>(GlobalErrorCode.FORBIDDEN);
        WebUtil.writeResp(response, JsonUtil.encode(result));
    }

}
