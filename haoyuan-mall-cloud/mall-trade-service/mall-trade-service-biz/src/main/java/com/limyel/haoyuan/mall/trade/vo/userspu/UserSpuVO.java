package com.limyel.haoyuan.mall.trade.vo.userspu;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSpuVO {

    private Long spuId;

    private String picUrl;

    private String spuName;

    private String summary;

    private Integer type;

    private Integer quantity;

    private LocalDateTime subscribeTime;

}
