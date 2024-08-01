package com.limyel.haoyuan.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册时新增
 * 删、查
 * 对外提供修改积分的接口（并发？），同时添加一条积分日志
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("member_point")
public class PointEntity extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 锁定的积分
     */
    private Integer lockedPoint;

}
