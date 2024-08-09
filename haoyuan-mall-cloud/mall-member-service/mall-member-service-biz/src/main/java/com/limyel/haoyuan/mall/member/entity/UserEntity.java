package com.limyel.haoyuan.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("member_user")
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
     * 积分
     */
    private Long point;

    /**
     * 余额
     */
    private Long balance;

    /**
     * 锁定积分
     */
    private Long lockedPoint;

    /**
     * 博客管理员ID
     */
    private Long blogAdminId;

    /**
     * 状态，0-封禁，1-正常
     */
    private Integer status;

}
