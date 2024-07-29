package com.limyel.haoyuan.common.core.exception;

import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String msg;

    /**
     * 错误明细，内部调试
     */
    private String detailMsg;

    public ServiceException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String msg) {
        this.msg = msg;
    }

    public ServiceException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

}
