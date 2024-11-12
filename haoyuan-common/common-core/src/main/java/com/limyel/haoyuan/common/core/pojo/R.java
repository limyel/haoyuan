package com.limyel.haoyuan.common.core.pojo;

import com.limyel.haoyuan.common.core.exception.ErrorCode;
import com.limyel.haoyuan.common.core.exception.GlobalErrorCode;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private T data;

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R<?> of(ErrorCode errorCode) {
        return new R<>(errorCode.getCode(), errorCode.getMsg(), null);
    }

    public static R<?> of(ServiceException e) {
        return new R<>(e.getCode(), e.getMessage(), null);
    }

    public static <T> R<?> ok(T data) {
        return new R<>(GlobalErrorCode.SUCCESS.getCode(), GlobalErrorCode.SUCCESS.getMsg(), data);
    }

    public static R<?> ok() {
        return R.of(GlobalErrorCode.SUCCESS);
    }

}
