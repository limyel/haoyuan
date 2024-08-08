package com.limyel.haoyuan.mall.product.vo.spu;

import lombok.Data;

@Data
public class SpuListVO {

    private String picUrl;

    private String name;

    private String summary;

    private Long price;

    private Integer stock;

    /**
     * 类型，0-一次性，1-订阅
     */
    private Integer type;

}
