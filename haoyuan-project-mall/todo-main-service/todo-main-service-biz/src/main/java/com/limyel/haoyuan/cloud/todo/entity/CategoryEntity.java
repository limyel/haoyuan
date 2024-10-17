package com.limyel.haoyuan.cloud.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("todo_category")
public class CategoryEntity extends BaseEntity {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分组 ID
     */
    private Long groupId;

}
