package com.limyel.haoyuan.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局错误码
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {
    SUCCESS(200, "成功"),
    INTERNAL_ERROR(500, "系统内部错误，请联系管理员")
    ;

    private Integer code;
    private String msg;

}
