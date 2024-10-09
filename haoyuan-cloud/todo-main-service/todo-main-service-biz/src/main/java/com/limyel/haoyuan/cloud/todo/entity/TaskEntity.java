package com.limyel.haoyuan.cloud.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("todo_task")
public class TaskEntity extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 截止时间
     */
    private LocalDateTime deadline;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 奖励积分
     */
    private Long point;

    /**
     * 重复类型，0-不重复
     */
    private Integer repeatType;

    /**
     * 重复 cron 表达式
     */
    private String repeatCron;

    /**
     * 状态
     */
    private Integer status;

}
