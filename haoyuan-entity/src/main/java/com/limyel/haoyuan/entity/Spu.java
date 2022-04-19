package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "spu")
public class Spu extends BaseEntity {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "subtitle", length = 800)
    private String subtitle;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "root_category_id")
    private Long rootCategoryId;

    @Column(name = "is_online", nullable = false)
    private Integer online;

    @Column(name = "price", nullable = false, length = 20)
    private String price;

    @Column(name = "sketch_spec_id")
    private Long sketchSpecId;

    @Column(name = "default_sku_id")
    private Long defaultSkuId;

    @Column(name = "img")
    private String img;

    @Column(name = "discount_price", length = 20)
    private String discountPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "tags", length = 30)
    private String tags;

    @Column(name = "is_test", nullable = false)
    private Boolean test;

    @Column(name = "for_theme_img")
    private String forThemeImg;

}