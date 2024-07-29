package com.limyel.haoyuan.blog.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_post")
public class PostEntity extends BaseEntity {

    private String title;

    private String slug;

    private String summary;

    private Boolean top;

    private Integer status;

    private Integer viewNum;

    private LocalDateTime publishTime;

    private Integer type;

    public int getCreateYear() {
        return getCreateTime().getYear();
    }

}
