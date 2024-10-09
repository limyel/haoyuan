package com.limyel.haoyuan.cloud.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("todo_sub_task")
public class SubTaskEntity extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 任务 ID
     */
    private Long taskId;

    /**
     * 截止时间
     */
    private LocalDateTime deadline;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 状态
     */
    private Integer status;

}
