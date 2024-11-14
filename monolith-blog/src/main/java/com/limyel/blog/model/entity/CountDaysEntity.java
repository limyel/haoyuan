package com.limyel.blog.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "count_days")
public class CountDaysEntity extends BaseEntity{

    private String name;

    private Integer status;

    private LocalDateTime startTime;

}
