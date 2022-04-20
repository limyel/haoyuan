package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "sku")
public class Sku extends BaseEntity {

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "discount_price", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "is_online", nullable = false)
    private Boolean online;

    @Column(name = "img")
    private String img;

    @Column(name = "title")
    private String title;

    @Column(name = "spu_id", nullable = false)
    private Long spuId;

    @Lob
    @Column(name = "specs")
    private String specs;

    @Column(name = "code")
    private String code;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "root_category_id")
    private Long rootCategoryId;

}