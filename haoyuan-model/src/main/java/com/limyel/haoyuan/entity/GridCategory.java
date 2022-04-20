package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "grid_category")
public class GridCategory extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "root_category_id")
    private Long rootCategoryId;

}