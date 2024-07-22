package com.limyel.haoyuan.platform.core.util.result;

public enum ResultCodeEnum {
    OK(0, "操作成功！"),

    NOT_FOUNT(40000, "请求的资源不存在！"),

    SERVER_ERROR(50000, "操作失败！"),
    ;

    public Integer code;

    public String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO<?> result() {
        return new ResultVO<>(this);
    }

    public ResultVO<?> result(Object obj) {
        return new ResultVO<>(obj, this);
    }

}
