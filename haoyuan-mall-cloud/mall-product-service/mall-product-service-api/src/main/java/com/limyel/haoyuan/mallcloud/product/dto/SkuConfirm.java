package com.limyel.haoyuan.mallcloud.product.dto;

import lombok.Data;

@Data
public class SkuConfirm {

    private Long id;

    private String spuName;

    private String name;

    private Long price;

    private Long memberLevelId;

    private Integer type;

}
