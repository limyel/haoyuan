package com.limyel.haoyuan.blog.store.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 增删
 * 修改数量、订阅时间
 * 查询
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("store_user_product")
public class UserProductEntity extends BaseEntity {

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 订阅时间
     */
    private LocalDateTime subscribeTime;

}
