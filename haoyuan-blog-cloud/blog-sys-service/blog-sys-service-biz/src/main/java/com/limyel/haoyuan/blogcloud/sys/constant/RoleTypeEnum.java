package com.limyel.haoyuan.blogcloud.sys.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTypeEnum {
    SYSTEM(1, "内置角色"),
    CUSTOM(2, "自定义角色")
    ;

    private final Integer type;
    private final String name;

}