package com.limyel.haoyuan.cloud.auth.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示未登录用户，用于在未认证的 Authentication 中传递的 principal
 */
@Data
@NoArgsConstructor
public class UnLoginUser {

    private String username;

    private String password;

    public UnLoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
