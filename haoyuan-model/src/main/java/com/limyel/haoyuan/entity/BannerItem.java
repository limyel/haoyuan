package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "banner_item")
public class BannerItem extends BaseEntity {

    @Column(name = "img")
    private String img;

    @Column(name = "keyword", length = 50)
    private String keyword;

    @Column(name = "name")
    private String name;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "banner_id", nullable = false)
    private Long bannerId;

}