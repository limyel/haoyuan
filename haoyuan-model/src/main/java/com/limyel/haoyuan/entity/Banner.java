package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "banner")
public class Banner extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;

}