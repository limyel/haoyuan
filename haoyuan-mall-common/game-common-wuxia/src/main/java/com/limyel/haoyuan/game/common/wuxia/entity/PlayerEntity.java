package com.limyel.haoyuan.game.common.wuxia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 玩家信息实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("member_level")
public class PlayerEntity extends BaseEntity {

    /**
     * 会员 ID
     */
    private Long memberUserId;

    /**
     * 生命
     */
    private Integer life;

    /**
     * 力量
     */
    private Integer power;

    /**
     * 速度
     */
    private Integer speed;

    /**
     * 经验值
     */
    private Integer experience;

}
