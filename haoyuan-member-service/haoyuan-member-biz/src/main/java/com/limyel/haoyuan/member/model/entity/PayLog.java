package com.limyel.haoyuan.member.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("haoyuan_paylog")
public class PayLog extends BaseEntity {

    private Long memberId;

    private Long point;

    private Long balance;

    private String reason;

}
