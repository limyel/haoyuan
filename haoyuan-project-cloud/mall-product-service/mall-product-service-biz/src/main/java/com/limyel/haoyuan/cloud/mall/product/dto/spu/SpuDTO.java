package com.limyel.haoyuan.cloud.mall.product.dto.spu;

import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class SpuDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
    private Long id;

    private String picUrl;

    @NotBlank(message = "商品名不能为空")
    private String name;

    private String summary;

    @Range(min = 0, max = 1, message = "商品类型范围异常")
    @NotNull(message = "商品类型不能为空")
    private Integer type;

    @Range(min = 0, max = 1, message = "商品状态范围异常")
    @NotNull(message = "商品状态不能为空")
    private Integer status;

}
