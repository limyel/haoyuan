package com.limyel.haoyuan.common.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServiceException(String msg) {
        this(GlobalErrorCode.INTERNAL_ERROR.getCode(), msg);
    }

    public ServiceException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

}
