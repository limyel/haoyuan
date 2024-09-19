package com.limyel.haoyuan.mall.common.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("product_stock_record")
public class StockRecordEntity extends BaseEntity {

    private Long skuId;

    private Integer quantity;

    private Integer skuNum;

    private String orderSn;

}
