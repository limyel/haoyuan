package com.limyel.haoyuan.common.core.exception.code;

public class InvalidParameterErrorCode extends ErrorCode {

    private static final String PREFIX = "InvalidParameter.";

    public static final ErrorCode ERROR_CODE = new ErrorCode("InvalidParameter", "参数错误");

    public InvalidParameterErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
