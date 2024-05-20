package com.limyel.haoyuan.system.dto.dept;

import com.limyel.haoyuan.common.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeptPageDTO extends PageParam {

    private String name;

    private Integer status;

}