package com.limyel.haoyuan.cloud.mall.product.dto.sku;

import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class SkuDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
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
