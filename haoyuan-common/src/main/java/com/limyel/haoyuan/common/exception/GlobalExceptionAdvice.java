package com.limyel.haoyuan.common.exception;

import com.limyel.haoyuan.common.api.Response;
import com.limyel.haoyuan.config.ExceptionCodeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfig exceptionCodeConfig;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<String> handleException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        String url = request.getRequestURI();
        String method = request.getMethod();
        return Response.failed(method + " " + url);
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Response<String> handleApiException(HttpServletRequest request, ApiException e) {
        String msg = exceptionCodeConfig.getMessage(e.getCode());
        log.error(msg);
        return new Response<>(e.getCode(), msg, this.formatExceptionData(request));
    }

    private String formatExceptionData(HttpServletRequest request) {
        return request.getMethod() + " " + request.getRequestURI();
    }
}
