package com.limyel.haoyuan.mallcloud.auth.entity;

import lombok.Data;

/**
 * 表示登录用户，用于在未认证的 Authentication 中传递的 princi
 */
@Data
public class LoginUser {

    private Long sysUserId;

    private Long memberUserId;

    private String username;

    private String password;

}
