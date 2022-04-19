package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "spu_img")
public class SpuImg extends BaseEntity {

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "spu_id")
    private Long spuId;

}