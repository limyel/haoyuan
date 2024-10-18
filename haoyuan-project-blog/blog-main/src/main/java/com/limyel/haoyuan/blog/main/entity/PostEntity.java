package com.limyel.haoyuan.blog.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_post")
public class PostEntity extends BaseEntity {

    private String title;

    private String slug;

    private String summary;

    private String content;

    private Boolean top;

    /**
     * 状态，0-隐藏 1-展示
     */
    private Integer status;

    private Integer viewNum;

    public int getCreateYear() {
        return getCreateTime().getYear();
    }

}
