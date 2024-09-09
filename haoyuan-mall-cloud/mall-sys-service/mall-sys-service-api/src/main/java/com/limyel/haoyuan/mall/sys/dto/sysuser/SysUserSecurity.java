package com.limyel.haoyuan.mall.sys.dto.sysuser;

import lombok.Data;

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

}
