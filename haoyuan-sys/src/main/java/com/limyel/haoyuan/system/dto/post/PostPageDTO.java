package com.limyel.haoyuan.system.dto.post;

import com.limyel.haoyuan.common.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostPageDTO extends PageParam {

    private String name;

    private Integer status;

}