package com.limyel.haoyuan.common.web.handler;

import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.common.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public R<?> handleBizException(BizException e) {
        log.error(e.getMessage());
        return new R<>(e.getCode(), e.getMessage());
    }

}