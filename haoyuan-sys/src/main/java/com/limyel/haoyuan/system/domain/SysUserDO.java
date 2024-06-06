package com.limyel.haoyuan.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class SysUserDO extends BaseDO {

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