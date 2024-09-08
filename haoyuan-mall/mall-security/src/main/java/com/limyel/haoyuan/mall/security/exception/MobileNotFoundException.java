package com.limyel.haoyuan.mall.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MobileNotFoundException extends AuthenticationException {

    public MobileNotFoundException(String msg) {
        super(msg);
    }

    public MobileNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
