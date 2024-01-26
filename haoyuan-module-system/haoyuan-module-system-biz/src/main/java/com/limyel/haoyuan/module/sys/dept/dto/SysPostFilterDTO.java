package com.limyel.haoyuan.module.sys.dept.dto;

import com.limyel.haoyuan.framework.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysPostFilterDTO extends PageParam {

    private String name;

    private Integer status;

}
