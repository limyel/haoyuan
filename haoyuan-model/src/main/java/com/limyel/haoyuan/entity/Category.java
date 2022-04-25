package com.limyel.haoyuan.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "category")
@Where(clause = "is_deleted = 0")
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_root", nullable = false)
    private Boolean root;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "img")
    private String img;

    @Column(name = "sequence")
    private Integer sequence;

}