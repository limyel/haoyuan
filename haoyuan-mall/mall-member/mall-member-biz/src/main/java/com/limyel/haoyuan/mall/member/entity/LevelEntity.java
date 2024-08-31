package com.limyel.haoyuan.mall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("member_level")
public class LevelEntity extends BaseEntity {

    /**
     * 等级名称
     */
    private String name;

    /**
     * 需要的积分
     */
    private Long point;

    /**
     * 展示顺序
     */
    private Integer sort;

}
