package com.limyel.haoyuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Boolean deleted;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否是根分类
     */
    @TableField(value = "is_root")
    private Boolean root;

    /**
     * 父分类id
     */
    private Long parentId;

    /**
     * 图片
     */
    private String img;

    /**
     * 排序位置
     */
    private Integer sequence;


}
