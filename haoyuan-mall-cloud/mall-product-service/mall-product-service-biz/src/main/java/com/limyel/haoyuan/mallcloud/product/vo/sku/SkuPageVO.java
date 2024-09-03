package com.limyel.haoyuan.mallcloud.product.vo.sku;

import lombok.Data;

@Data
public class SkuPageVO {

    private Long id;

    private String spuName;

    private String name;

    private Long price;

    private Integer stock;

    private String memberLevel;

}
