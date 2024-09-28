package com.limyel.haoyuan.game.common.wuxia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 帮派实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("member_level")
public class FactionEntity extends BaseEntity {

    private String name;

    private Long leader;

    private Long schoolId;

}
