package com.limyel.haoyuan.common.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.core.exception.ErrorCode;
import com.limyel.haoyuan.common.core.exception.GlobalErrorCode;
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
    private Integer code = 0;

    private String msg = "success";

    private T data;

    public R(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> ok(T data) {
        R<T> result = new R<>();
        result.setData(data);
        return result;
    }

    public boolean success() {
        return code == 0;
    }

    public static <T> R<T> error(int code) {
        return null;
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