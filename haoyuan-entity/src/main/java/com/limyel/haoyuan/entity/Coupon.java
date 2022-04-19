package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "coupon")
public class Coupon extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "description")
    private String description;

    @Column(name = "full_money", precision = 10, scale = 2)
    private BigDecimal fullMoney;

    @Column(name = "minus", precision = 10, scale = 2)
    private BigDecimal minus;

    @Column(name = "rate", precision = 10, scale = 2)
    private BigDecimal rate;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "remark")
    private String remark;

    @Column(name = "is_whole", nullable = false)
    private Integer whole;

}