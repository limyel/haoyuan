package com.limyel.haoyuan.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("product_spu")
public class SpuEntity extends BaseEntity {

    /**
     * 商品图片
     */
    private String picUrl;

    /**
     * 名称
     */
    private String name;

    /**
     * 摘要
     */
    private String summary;

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
     * 类型，0-一次性，1-订阅
     */
    private Integer type;

    /**
     * 状态，0-下架 1-正常
     */
    private Integer status;

    /**
     * 可购买的会员等级
     */
    private Long memberLevelId;

}
