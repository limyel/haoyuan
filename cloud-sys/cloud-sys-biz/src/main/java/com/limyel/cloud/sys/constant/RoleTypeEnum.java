package com.limyel.cloud.sys.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleTypeEnum {
    SYSTEM("系统内置角色", 0),
    CUSTOMER("用户自定义角色", 1)
    ;

    private final String label;
    private final Integer value;

}
