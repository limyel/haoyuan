package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "theme")
public class Theme extends BaseEntity {

    @Column(name = "title", length = 60)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "tpl_name", length = 30)
    private String tplName;

    @Column(name = "entrance_img")
    private String entranceImg;

    @Column(name = "extend")
    private String extend;

    @Column(name = "internal_top_img")
    private String internalTopImg;

    @Column(name = "title_img")
    private String titleImg;

    @Column(name = "is_online", nullable = false)
    private Boolean online = false;

}