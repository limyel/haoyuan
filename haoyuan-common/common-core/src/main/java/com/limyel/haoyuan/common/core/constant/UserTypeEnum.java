package com.limyel.haoyuan.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    /**
     * 管理后台用户
     */
    SYS_USER("sys_user"),
    /**
     * app 端用户
     */
    APP_USER("app_user")
    ;

    private final String type;

}
