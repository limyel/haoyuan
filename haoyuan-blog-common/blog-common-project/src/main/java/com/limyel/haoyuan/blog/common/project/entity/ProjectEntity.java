package com.limyel.haoyuan.blog.common.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("project_project")
public class ProjectEntity extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 项目地址
     */
    private String url;

    /**
     * 状态，0-停止维护 1-维护
     */
    private Integer status;

}
