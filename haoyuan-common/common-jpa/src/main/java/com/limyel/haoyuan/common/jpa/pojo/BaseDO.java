package com.limyel.haoyuan.common.jpa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDO {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private Long id;

   @Column(name = "create_by")
   private Long createBy;

   @Column(name = "create_time", nullable = false)
   private LocalDateTime createTime;

   @Column(name = "update_by")
   private Long updateBy;

   @Column(name = "update_time", nullable = false)
   private LocalDateTime updateTime;

}
