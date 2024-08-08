package com.limyel.haoyuan.mall.product.dto.spu;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpuListDTO extends PageParam {

    private String name;

    private Integer type;

}
