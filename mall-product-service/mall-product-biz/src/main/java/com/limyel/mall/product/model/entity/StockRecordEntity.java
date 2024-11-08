package com.limyel.mall.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("mall_stock_record")
public class StockRecordEntity extends BaseEntity {

    private Long productId;

    private Integer quantity;

    private String orderSn;

}
