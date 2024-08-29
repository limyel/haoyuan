package com.limyel.haoyuan.mall.trade.vo.userspu;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProductVO {

    private Long skuId;

    private String spuName;

    private String skuName;

    private Integer type;

    private Integer quantity;

    private LocalDateTime subscribeTime;

}
