package com.limyel.haoyuan.framework.web.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码，0表示成功，非0表示失败
     */
    private Integer code = 0;

    private String msg = "success";

    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.setData(data);
        return result;
    }

    public boolean success() {
        return code == 0;
    }

    public static <T> Result<T> error(int code) {
        return null;
    }

}
