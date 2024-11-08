package com.limyel.mall.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("mall_product")
public class ProductEntity extends BaseEntity {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品单价（分）
     */
    private Long price;

    /**
     * 商品描述
     */
    private String desc;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 状态，
     */
    private Integer status;

}
