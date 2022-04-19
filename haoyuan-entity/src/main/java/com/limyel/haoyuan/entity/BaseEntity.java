package com.limyel.haoyuan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(insertable = false, updatable = false)
    private Date createTime;

    @JsonIgnore
    @Column(insertable = false, updatable = false)
    private Date updateTime;

    @JsonIgnore
    @Column(name = "is_deleted")
    private Boolean deleted;

}
