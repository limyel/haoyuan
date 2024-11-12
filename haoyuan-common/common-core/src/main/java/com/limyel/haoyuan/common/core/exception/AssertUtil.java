package com.limyel.haoyuan.common.core.exception;

public class AssertUtil {

    public static void assertTrue(boolean flag) {
        if (!flag) {
            throw new ServiceException(GlobalErrorCode.INTERNAL_ERROR);
        }
    }

    public static void assertTrue(boolean flag, ErrorCode errorCode) {
        if (!flag) {
            throw new ServiceException(errorCode);
        }
    }

}
