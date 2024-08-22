package com.limyel.haoyuan.common.cloud.exception;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public R<?> handleFeignException(FeignException e, HttpServletRequest request) {
        String content = e.contentUTF8();
        if (!StringUtils.hasText(content)) {
            return R.failed(e.getMessage());
        }
        return JSONUtil.parseObject(content, R.class);
    }
}
