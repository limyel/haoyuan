package com.limyel.haoyuan.mall.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("trade_user_spu")
public class UserSpuEntity extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long spuId;

    /**
     * 商品图片
     */
    private String picUrl;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 摘要
     */
    private String summary;

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
