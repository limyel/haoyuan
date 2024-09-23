package com.limyel.haoyuan.blog.common.project.dto.project;

import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class ProjectDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
    private Long id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String summary;

    @NotBlank(message = "项目地址不能为空")
    private String url;

    @Range(min = 0, max = 1, message = "状态范围错误")
    private Integer status;

}
