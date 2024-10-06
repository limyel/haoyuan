package com.limyel.haoyuan.mall.common.product.vo.spu;

import lombok.Data;

@Data
public class SpuListVO {

    private Long id;

    private String picUrl;

    private String name;

    private String summary;

    /**
     * 类型，0-一次性，1-订阅
     */
    private Integer type;

}
