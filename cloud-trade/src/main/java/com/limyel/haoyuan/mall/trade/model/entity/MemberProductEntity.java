package com.limyel.haoyuan.mall.trade.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("mall_member_product")
public class MemberProductEntity extends BaseEntity {

    /**
     * 会员 ID
     */
    private Long memberId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer quantity;

}
