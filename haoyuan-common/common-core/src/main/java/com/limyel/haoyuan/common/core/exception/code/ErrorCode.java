package com.limyel.haoyuan.common.core.exception.code;

import lombok.Getter;

@Getter
public class ErrorCode {

    private final String code;

    private final String msg;

    public ErrorCode(String code, String message) {
        this.code = code;
        this.msg = message;
    }

}