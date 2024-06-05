package com.limyel.haoyuan.common.core.exception.code;

public class InvalidParameterErrorCode extends ErrorCode {

    private static final String PREFIX = "InvalidParameter.";

    public InvalidParameterErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
