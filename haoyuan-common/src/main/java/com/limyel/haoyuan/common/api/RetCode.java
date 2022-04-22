package com.limyel.haoyuan.common.api;

/**
 * 响应状态码
 */
public enum RetCode {
    SUCCESS(0, "成功", "success"),
    FAILED(1, "失败", "failed"),

    // 资源相关
    NOT_FOUND(101, "资源不存在", "not found")

    ;

    private Integer code;
    private String msg;
    private String msgEN;

    RetCode(Integer code, String msg, String msgEN) {
        this.code = code;
        this.msg = msg;
        this.msgEN = msgEN;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgEN() {
        return msgEN;
    }

    public void setMsgEN(String msgEN) {
        this.msgEN = msgEN;
    }
}
