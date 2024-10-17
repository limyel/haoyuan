package com.limyel.haoyuan.cloud.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("todo_group")
public class GroupEntity extends BaseEntity {

    /**
     * 分组名称
     */
    private String name;

}
