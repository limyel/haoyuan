package com.limyel.haoyuan.game.common.wuxia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 门派实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("wuxia_school")
public class SchoolEntity extends BaseEntity {

    private String name;

}
