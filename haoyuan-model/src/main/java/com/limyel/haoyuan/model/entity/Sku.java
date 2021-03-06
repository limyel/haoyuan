package com.limyel.haoyuan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * sku
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
public class Sku implements Serializable {

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
     * 原价
     */
    private BigDecimal price;

    /**
     * 折扣价
     */
    private BigDecimal discountPrice;

    /**
     * 是否上线
     */
    @TableField(value = "is_online")
    private Boolean online;

    /**
     * 图片
     */
    private String img;

    /**
     * 标题
     */
    private String title;

    /**
     * spu id
     */
    private Long spuId;

    /**
     * 规格
     */
    private String specs;

    /**
     * 代码
     */
    private String code;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 根分类id
     */
    private Long rootCategoryId;


}
