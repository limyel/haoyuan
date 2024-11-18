package com.limyel.cloud.sys.model.dto.role;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePageDTO extends PageParam {

    private String name;

    private String code;

    private Integer status;

}
