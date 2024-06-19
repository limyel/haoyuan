package com.limyel.haoyuan.blog.main.dto.post;

import com.limyel.haoyuan.common.web.validate.Create;
import com.limyel.haoyuan.common.web.validate.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "文章请求体")
public class PostDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
    @ApiModelProperty(value = "ID")
    private Long id;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String slug;

    @NotBlank(message = "摘要不能为空")
    private String summary;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotNull(message = "是否置顶不能为空")
    private Boolean top;

    @Range(min = 0, max = 1, message = "状态范围错误")
    private Integer status;

    private LocalDateTime publishTime;

    @NotEmpty(message = "标签不能为空")
    private List<Long> tagIds;

}
