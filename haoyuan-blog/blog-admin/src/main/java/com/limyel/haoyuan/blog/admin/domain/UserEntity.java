package com.limyel.haoyuan.blog.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("admin_user")
public class UserEntity extends BaseEntity {

    private String username;

    private String password;

}
