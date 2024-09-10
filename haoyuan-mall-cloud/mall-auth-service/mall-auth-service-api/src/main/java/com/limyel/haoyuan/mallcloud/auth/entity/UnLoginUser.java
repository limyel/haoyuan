package com.limyel.haoyuan.mallcloud.auth.entity;

import lombok.Data;

/**
 * 表示未登录用户，用于在未认证的 Authentication 中传递的 principal
 */
@Data
public class UnLoginUser {

    private String username;

    private String password;

}
