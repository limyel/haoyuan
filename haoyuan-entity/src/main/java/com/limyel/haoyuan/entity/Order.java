package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "`order`")
public class Order extends BaseEntity {

    @Column(name = "order_no", length = 20)
    private String orderNo;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "total_count")
    private Integer totalCount;

    @Column(name = "expired_time")
    private Date expiredTime;

    @Column(name = "placed_time")
    private Date placedTime;

    @Column(name = "snap_img")
    private String snapImg;

    @Column(name = "snap_title")
    private String snapTitle;

    @Lob
    @Column(name = "snap_items")
    private String snapItems;

    @Lob
    @Column(name = "snap_address")
    private String snapAddress;

    @Column(name = "prepay_id")
    private String prepayId;

    @Column(name = "final_total_price", precision = 10, scale = 2)
    private BigDecimal finalTotalPrice;

    @Column(name = "status")
    private Integer status;

}