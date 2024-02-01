package com.limyel.haoyuan.module.system.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleCodeEnum {
    SUPER_ADMIN("super_admin", "超级管理员")
    ;

    private String code;
    private String name;

    public static Boolean isSuperAdmin(String code) {
        return SUPER_ADMIN.code.equals(code);
    }

}
