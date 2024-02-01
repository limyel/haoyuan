package com.limyel.haoyuan.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    private Integer code;

    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public BizException() {
    }

    public BizException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
