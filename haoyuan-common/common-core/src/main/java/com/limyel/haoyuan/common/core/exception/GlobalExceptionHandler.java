package com.limyel.haoyuan.common.core.exception;

import com.limyel.haoyuan.common.core.pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public R<?> handleServiceException(ServiceException e) {
        log.error("业务处理异常：{}", e.getMessage());
        return R.of(e);
    }

}
