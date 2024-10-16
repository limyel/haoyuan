package com.limyel.haoyuan.blog.common.main.dto.post;

import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class PostDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
    private Long id;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "slug 不能为空")
    private String slug;

    private String summary;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotNull(message = "是否置顶不能为空")
    private Boolean top;

    @NotNull
    @Range(min = 0, max = 1, message = "状态范围错误")
    private Integer status;

    @NotEmpty(message = "标签不能为空")
    private List<Long> tagIds;

}
