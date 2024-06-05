package com.limyel.haoyuan.common.core.exception.code;

public class InternalErrorCode extends ErrorCode {

    private static final String PREFIX = "InternalError.";

    public InternalErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
