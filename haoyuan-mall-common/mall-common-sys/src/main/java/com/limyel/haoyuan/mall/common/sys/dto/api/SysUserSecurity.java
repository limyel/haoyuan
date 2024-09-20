package com.limyel.haoyuan.mall.common.sys.dto.api;

import lombok.Data;

import java.util.Set;

@Data
public class SysUserSecurity {

    private Long id;

    private String username;

    private Integer type;

    private String email;

    private String mobile;

    private String avatar;

    private String password;

    private Integer status;

    private Set<String> permissions;

}
