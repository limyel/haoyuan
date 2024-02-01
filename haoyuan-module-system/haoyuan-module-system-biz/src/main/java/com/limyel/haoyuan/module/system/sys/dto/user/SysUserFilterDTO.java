package com.limyel.haoyuan.module.system.sys.dto.user;

import com.limyel.haoyuan.framework.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserFilterDTO extends PageParam {

    private String username;

    private Long deptId;

    private String mobile;

    private Integer status;

    private LocalDateTime[] createTime;

}
