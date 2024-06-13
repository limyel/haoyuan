package com.limyel.haoyuan.common.core.exception.code;

public class FailedOperationErrorCode extends ErrorCode {

    private static final String PREFIX = "FailedOperation.";

    public static final ErrorCode ERROR_CODE = new ErrorCode("FailedOperation", "操作失败");

    public FailedOperationErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
