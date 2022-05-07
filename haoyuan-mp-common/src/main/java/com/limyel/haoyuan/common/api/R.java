package com.limyel.haoyuan.common.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class R<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public R() {}

    public R(int code, String msg) {
        this(code, msg, null);
    }
    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> success() {
        return new R<>(RetCode.SUCCESS.getCode(), RetCode.SUCCESS.getMsg());
    }

    public static <T> R<T> success(T data) {
        R<T> r = success();
        r.setData(data);
        return r;
    }

    public static <T> R<T> success(String msg, T data) {
        return new R<T>(RetCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> R<T> successMsg(String msg) {
        R<T> r = success();
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> error() {
        return new R<T>(RetCode.ERROR.getCode(), RetCode.ERROR.getMsg());
    }

    public static <T> R<T> error(T data) {
        R<T> r = error();
        r.setData(data);
        return r;
    }

    public static <T> R<T> errorMsg(String msg) {
        R<T> r = error();
        r.setMsg(msg);
        return r;
    }

}

