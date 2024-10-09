package com.limyel.haoyuan.cloud.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("todo_task_snapshoot")
public class TaskSnapshootEntity extends BaseEntity {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 奖励积分
     */
    private Long point;

    /**
     * 实际获得的积分
     */
    private Long actualPoint;

}
