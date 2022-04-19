package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sale_explain")
public class SaleExplain extends BaseEntity {

    @Column(name = "is_fixed", nullable = false)
    private Boolean fixed;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @Column(name = "replace_id")
    private Long replaceId;

    @Column(name = "spu_id")
    private Long spuId;

}