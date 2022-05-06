package com.limyel.haoyuan.portal.entity;

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
 * 订单
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
public class Order implements Serializable {

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
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 总量
     */
    private Integer totalCount;

    /**
     * 过期时间
     */
    private LocalDateTime expiredTime;

    /**
     * 下单时间
     */
    private LocalDateTime placedTime;

    /**
     * 快照图片
     */
    private String snapImg;

    /**
     * 快照标题
     */
    private String snapTitle;

    /**
     * 快照items
     */
    private String snapItems;

    /**
     * 快照地址
     */
    private String snapAddress;

    /**
     * 预支付id
     */
    private String prepayId;

    /**
     * 最终总价
     */
    private BigDecimal finalTotalPrice;

    /**
     * 状态
     */
    private Integer status;


}
