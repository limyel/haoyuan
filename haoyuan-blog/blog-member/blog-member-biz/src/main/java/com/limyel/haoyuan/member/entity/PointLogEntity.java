package com.limyel.haoyuan.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("member_point_log")
public class PointLogEntity extends BaseEntity {

    /**
     * 积分ID
     */
    private Long pointId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 变动类型，0-减少，1-增加
     */
    private Integer type;

    /**
     * 变动的积分
     */
    private Integer changedPoint;

    /**
     * 变动原因
     */
    private String reason;

}
