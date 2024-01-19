package com.limyel.haoyuan.module.system.dept.dto.req;

import com.limyel.haoyuan.framework.web.pojo.PageParam;
import lombok.Data;

@Data
public class SysPostFilterReq extends PageParam {

    private String name;

    private Integer status;

}
