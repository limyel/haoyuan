package com.limyel.haoyuan.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("member_pay_log")
public class PayLogEntity extends BaseEntity {

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
    private Long changedPoint;

    /**
     * 变动的余额
     */
    private Long changedBalance;

    /**
     * 变动原因
     */
    private String reason;

}
