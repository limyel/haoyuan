package com.limyel.haoyuan.mall.sys.dto.sysuser;

import lombok.Data;

@Data
public class SysUserInfoDTO {

    private Long id;

    private String username;

    private Integer type;

    private String email;

    private String mobile;

    private String avatar;

    private String password;

    private Integer status;

}
