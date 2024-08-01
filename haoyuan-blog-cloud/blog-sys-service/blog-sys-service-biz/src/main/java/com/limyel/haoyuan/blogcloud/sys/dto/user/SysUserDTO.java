package com.limyel.haoyuan.blogcloud.sys.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class SysUserDTO {

    private Long id;

    private String username;

    private String password;

    private Long deptId;

    private Set<Long> postIds;

    private String email;

    private String mobile;

    private Integer gender;

    private String avatar;

    private Boolean superAdmin;

    private Integer status;

    private String remark;

}