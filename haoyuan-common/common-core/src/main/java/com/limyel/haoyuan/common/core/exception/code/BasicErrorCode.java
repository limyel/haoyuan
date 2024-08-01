package com.limyel.haoyuan.common.core.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * todo ?
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BasicErrorCode implements ErrorCode {
    OK(0, "成功"),
    FAILED(500, "请求失败"),

    UNAUTHORIZED(401, "认证失败"),
    FORBIDDEN(403, "没有访问权限"),
    ;

    private Integer code;
    private String msg;

}
