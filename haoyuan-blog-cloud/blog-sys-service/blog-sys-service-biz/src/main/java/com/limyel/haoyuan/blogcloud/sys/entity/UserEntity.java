package com.limyel.haoyuan.blogcloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class UserEntity extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号状态，0-封禁，1-正常
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}