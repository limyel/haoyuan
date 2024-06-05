package com.limyel.haoyuan.common.core.exception.code;

public class FailedOperationErrorCode extends ErrorCode {

    private static final String PREFIX = "FailedOperation.";

    public FailedOperationErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
