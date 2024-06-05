package com.limyel.haoyuan.common.core.exception.code;

public class AuthFailureErrorCode extends ErrorCode {

    private static final String PREFIX = "AuthFailure.";

    public AuthFailureErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
