package com.limyel.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("count_days")
public class CountDaysEntity extends BaseEntity {

    private String name;

    private Integer status;

    private LocalDateTime startTime;

}
