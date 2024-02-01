package com.limyel.haoyuan.module.system.sys.dto.post;

import com.limyel.haoyuan.framework.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostPageDTO extends PageParam {

    private String name;

    private Integer status;

}
