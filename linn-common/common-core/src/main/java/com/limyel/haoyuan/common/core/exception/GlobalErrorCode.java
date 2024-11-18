package com.limyel.haoyuan.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局错误码
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {
    SUCCESS(0, "成功"),
    INTERNAL_ERROR(500, "系统内部错误，请联系管理员")
    ;

    private final Integer code;
    private final String msg;

}
