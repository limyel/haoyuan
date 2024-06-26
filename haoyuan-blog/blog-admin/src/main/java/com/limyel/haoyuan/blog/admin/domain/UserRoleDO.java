package com.limyel.haoyuan.blog.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("admin_user_role")
public class UserRoleDO extends BaseDO {

    private Long userId;

    private String role;

}
