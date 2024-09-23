package com.limyel.haoyuan.blog.common.project.dto.project;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectPageDTO extends PageParam {

    private String name;

    private Integer status;

}
