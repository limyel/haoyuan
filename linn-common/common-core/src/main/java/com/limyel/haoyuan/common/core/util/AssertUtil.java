package com.limyel.haoyuan.common.core.util;

import com.limyel.haoyuan.common.core.exception.ErrorCode;
import com.limyel.haoyuan.common.core.exception.GlobalErrorCode;
import com.limyel.haoyuan.common.core.exception.ServiceException;

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
