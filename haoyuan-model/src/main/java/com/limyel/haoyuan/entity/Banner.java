package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "banner")
public class Banner extends BaseEntity {

    public enum Type {

    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;

    @Column(name = "type")
    private Type type;

    @Column(name = "keyword")
    private String keyword;

}