package com.limyel.haoyuan.common.jpa.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {

   private Long id;

   private Long createBy;

   private LocalDateTime createTime;

   private Long updateBy;

   private LocalDateTime updateTime;

}
