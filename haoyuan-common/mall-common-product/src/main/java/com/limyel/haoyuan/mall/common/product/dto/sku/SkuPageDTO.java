package com.limyel.haoyuan.mall.common.product.dto.sku;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SkuPageDTO extends PageParam {

    private Long spuId;

    private String name;

    private Long memberLevelId;

}
