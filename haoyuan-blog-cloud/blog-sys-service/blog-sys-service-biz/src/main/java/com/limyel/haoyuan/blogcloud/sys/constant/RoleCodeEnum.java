package com.limyel.haoyuan.blogcloud.sys.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleCodeEnum {
    SUPER_ADMIN("super_admin", "超级管理员")
    ;

    private final String code;
    private final String name;

    public static Boolean isSuperAdmin(String code) {
        return SUPER_ADMIN.code.equals(code);
    }

}