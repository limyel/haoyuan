package com.limyel.haoyuan.mallcloud.product.vo.spu;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSpuVO {

    private String picUrl;

    private String name;

    private Integer type;

    private Integer quantity;

    private LocalDateTime subscribeTime;

}
