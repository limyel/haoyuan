package com.limyel.haoyuan.common.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.limyel.haoyuan.common.core.exception.code.BasicErrorCode;
import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.ServiceException;
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
    private Integer code;

    private String msg;

    private T data;

    public R(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static R<?> of(ErrorCode errorCode) {
        return new R<>(errorCode);
    }

    public static R<?> of(Integer code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> of(Integer code, String msg, T data) {
        R<T> r = new R<>(code, msg);
        r.setData(data);
        return r;
    }

    public static R<?> ok() {
        return new R<>(BasicErrorCode.OK);
    }

    public static <T> R<T> ok(T data) {
        R<T> result = new R<>(BasicErrorCode.OK);
        result.setData(data);
        return result;
    }

    public static R<?> check(int rows) {
        return rows > 0 ? R.ok() : R.failed();
    }

    public static R<?> failed() {
        return new R<>(BasicErrorCode.FAILED);
    }

    public static R<?> failed(String msg) {
        return new R<>(BasicErrorCode.FAILED.getCode(), msg);
    }

    @JsonIgnore
    public void checkError() throws ServiceException {
        if (BasicErrorCode.OK.getCode().equals(code)) {
            return;
        }
        throw new ServiceException(code, msg);
    }

    @JsonIgnore
    public T getCheckedData() {
        checkError();
        return data;
    }

}