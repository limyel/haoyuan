package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity")
public class Activity extends BaseEntity {

    @Column(name = "title", length = 60)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "remark", length = 60)
    private String remark;

    @Column(name = "is_online", nullable = false)
    private Boolean online = false;

    @Column(name = "entrance_img")
    private String entranceImg;

    @Column(name = "internal_top_img")
    private String internalTopImg;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

}