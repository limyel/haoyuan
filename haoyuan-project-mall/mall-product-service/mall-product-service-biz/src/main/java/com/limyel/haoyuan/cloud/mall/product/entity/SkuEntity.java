package com.limyel.haoyuan.cloud.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("product_sku")
public class SkuEntity extends BaseEntity {

    /**
     * 商品ID
     */
    private Long spuId;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格（分）
     */
    private Long price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 库存锁定数量
     */
    private Integer lockedStock;

    /**
     * 状态，0-下架 1-正常
     */
    private Integer status;

}
