package com.limyel.haoyuan.module.member.vo;

import com.limyel.haoyuan.module.product.api.sku.vo.SkuVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {

    private String username;

    private SkuVO sku;

}
