package com.limyel.haoyuan.common.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.GlobalErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码，0表示成功，非0表示失败
     */
    private String code = GlobalErrorCode.SUCCESS.getCode();

    private String msg = GlobalErrorCode.SUCCESS.getMsg();

    private T data;

    public R(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static R<?> ok() {
        return new R<>();
    }

    public R(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> ok(T data) {
        R<T> result = new R<>();
        result.setData(data);
        return result;
    }

    public boolean success() {
        return code.equals(GlobalErrorCode.SUCCESS.getCode());
    }

    public static R<?> failed(ErrorCode errorCode) {
        R<?> result = new R<>();
        result.setCode(errorCode.getCode());
        result.setMsg(errorCode.getMsg());
        return result;
    }

    @JsonIgnore
    public void checkError() throws BizException {
        if (GlobalErrorCode.SUCCESS.getCode().equals(code)) {
            return;
        }
        throw new BizException(code, msg);
    }

    @JsonIgnore
    public T getCheckedData() {
        checkError();
        return data;
    }

}