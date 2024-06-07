package com.limyel.haoyuan.common.core.exception;

import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.GlobalErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    private String code;

    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public BizException() {
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public BizException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(String message, Throwable e) {
        super(message, e);
        this.code = GlobalErrorCode.INTERNAL_ERROR.getCode();
        this.message = message;
    }

}