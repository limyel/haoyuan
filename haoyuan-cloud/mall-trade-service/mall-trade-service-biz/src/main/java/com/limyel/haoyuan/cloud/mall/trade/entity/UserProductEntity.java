package com.limyel.haoyuan.cloud.mall.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("trade_user_product")
public class UserProductEntity extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * SPU 名称
     */
    private String spuName;

    /**
     * SKU 名称
     */
    private String skuName;

    /**
     * 类型，0-一次性，1-订阅
     */
    private Integer type;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 订阅时间
     */
    private LocalDateTime subscribeTime;

}
