package com.limyel.haoyuan.common.satoken.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    ADMIN("admin", "管理后台"),
    USER("user", "客户端")
    ;

    private final String value;
    private final String name;
}
