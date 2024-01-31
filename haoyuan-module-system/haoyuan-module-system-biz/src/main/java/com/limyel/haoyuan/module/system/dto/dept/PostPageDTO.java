package com.limyel.haoyuan.module.system.dto.dept;

import com.limyel.haoyuan.framework.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostPageDTO extends PageParam {

    private String name;

    private Integer status;

}
