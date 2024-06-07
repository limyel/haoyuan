package com.limyel.haoyuan.admin.security;

import com.limyel.haoyuan.common.core.exception.code.AuthFailureErrorCode;
import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.FailedOperationErrorCode;
import org.springframework.security.core.AuthenticationException;

public class UsernameOrPasswordNullException extends AuthenticationException {

    public static final ErrorCode ERROR_CODE = new AuthFailureErrorCode("CommonSecurity.UsernameOrPasswordNull"
            , "用户名或密码为空");

    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
