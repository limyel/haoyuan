package com.limyel.haoyuan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * spu
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
public class Spu implements Serializable {

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
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subtitle;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 根分类id
     */
    private Long rootCategoryId;

    /**
     * 是否上线
     */
    @TableField(value = "is_online")
    private Boolean online;

    /**
     * 价格
     */
    private String price;

    /**
     * 某种规格可以直接附加单品图片
     */
    private Long sketchSpecId;

    /**
     * 默认选择的sku
     */
    private Long defaultSkuId;

    /**
     * 图片
     */
    private String img;

    /**
     * 折扣价格
     */
    private String discountPrice;

    /**
     * 描述
     */
    private String description;

    /**
     * 标签
     */
    private String tags;

    /**
     * 是否是测试
     */
    @TableField(value = "is_test")
    private Boolean test;

    private String forThemeImg;


}
