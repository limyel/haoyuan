package com.limyel.haoyuan.mall.common.product.dto.stockrule;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StockRulePageDTO extends PageParam {

    private Long spuId;

    private Integer type;

    private Integer status;

}
