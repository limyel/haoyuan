package com.limyel.haoyuan.blog.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("wiki_cover")
public class CoverDO extends BaseDO {

    /**
     * 标题
     */
    private String title;

    /**
     * slug
     */
    private String slug;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 状态
     */
    private Integer status;

}
