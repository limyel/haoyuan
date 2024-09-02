package com.limyel.haoyuan.mall.product.vo.sku;

import lombok.Data;

@Data
public class SkuListVO {

    private Long id;

    private String name;

    private Long price;

    private Integer stock;

    private String memberLevel;

}
