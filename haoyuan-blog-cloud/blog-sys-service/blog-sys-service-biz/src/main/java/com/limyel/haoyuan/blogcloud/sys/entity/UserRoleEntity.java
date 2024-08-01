package com.limyel.haoyuan.blogcloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_role")
public class UserRoleEntity extends BaseEntity {

    private Long userId;

    private Long roleId;

}