package com.limyel.haoyuan.common.core.exception.auth;

import com.limyel.haoyuan.common.core.exception.code.AuthFailureErrorCode;
import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.FailedOperationErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * jwt token 异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BadTokenException extends RuntimeException {

    public static final ErrorCode ERROR_CODE = new AuthFailureErrorCode("CommonCore", "用户名或密码错误");

    private String msg;

    public BadTokenException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

}
