package com.limyel.haoyuan.platform.core.enhancer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.limyel.haoyuan.platform.core.constant.PlatforConstants.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlatformException.class)
    public String handlePlatformException(PlatformException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        HashMap<String, Object> map = new HashMap<>();
        map.put(EXCEPTION_MSG, e.getMessage());
        request.setAttribute(ERROR_DATA, map);
        request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return "forward:/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        Map<String, Object> map = new HashMap<>();
        map.put(ERROR_MSG, "服务异常，请联系管理员。");
        map.put(EXCEPTION_MSG, e.getMessage());
        request.setAttribute(ERROR_DATA, map);
        request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return "forward:/error"; // 不破坏 SpringMVC 的内容协商
    }

}
