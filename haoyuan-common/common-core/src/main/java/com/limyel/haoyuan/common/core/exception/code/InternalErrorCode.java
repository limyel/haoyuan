package com.limyel.haoyuan.common.core.exception.code;

/**
 * 内部错误
 */
public class InternalErrorCode extends ErrorCode {

    private static final String PREFIX = "InternalError.";

    public static final ErrorCode ERROR_CODE = new ErrorCode("InternalError", "内部错误");

    public InternalErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
