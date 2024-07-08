package com.limyel.haoyuan.blog.wiki.dto.cover;

import com.limyel.haoyuan.common.web.validate.Create;
import com.limyel.haoyuan.common.web.validate.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class CoverDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
    @ApiModelProperty("ID")
    private Long id;

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @NotBlank(message = "slug 不能为空")
    @ApiModelProperty(value = "slug", required = true)
    private String slug;

    @NotNull
    @Range(min = 0, max = 1, message = "状态范围错误")
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

}
