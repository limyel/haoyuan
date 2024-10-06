package com.limyel.haoyuan.mall.common.product.dto.spu;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpuPageDTO extends PageParam {

    private String name;

    private Integer status;

    private Integer type;
    
}
