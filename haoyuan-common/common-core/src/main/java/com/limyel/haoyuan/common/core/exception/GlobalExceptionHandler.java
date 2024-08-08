package com.limyel.haoyuan.common.core.exception;

import com.limyel.haoyuan.common.core.pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public R<?> handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMsg());
        Integer code = e.getCode();
        String msg = e.getMsg();
        return code == null ? R.failed(msg) : R.of(code, msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public R<?> handleException(Exception e, HttpServletRequest request) {
        return R.failed(e.getMessage());
    }

}
