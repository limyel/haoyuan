package com.limyel.haoyuan.common.exception;

import lombok.Data;

@Data
public class ErrorCode {

    private final Integer code;

    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
