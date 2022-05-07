package com.limyel.haoyuan.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    protected Integer code;

    protected Integer httpStatusCode = 500;

    public ApiException(Integer code) {
        this.code = code;
    }

}
