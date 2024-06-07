package com.limyel.haoyuan.common.core.exception.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TokenExpiredException extends RuntimeException {

    private String msg;

    public TokenExpiredException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

}
