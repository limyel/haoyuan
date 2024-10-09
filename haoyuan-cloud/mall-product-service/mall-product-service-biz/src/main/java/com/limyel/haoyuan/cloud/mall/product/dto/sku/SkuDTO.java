package com.limyel.haoyuan.cloud.mall.product.dto.sku;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SkuDTO {

    private Long id;

    @NotNull(message = "SPU 不能为空")
    private Long spuId;

    @NotBlank(message = "SKU 名称不能为空")
    private String name;

    @Min(value = 0, message = "商品价格不能低于 0")
    @NotNull(message = "商品价格不能为空")
    private Long price;

    @Min(value = 0, message = "库存不能低于 0")
    @NotNull(message = "库存不能为空")
    private Integer stock;

    private Long memberLevelId;

}
