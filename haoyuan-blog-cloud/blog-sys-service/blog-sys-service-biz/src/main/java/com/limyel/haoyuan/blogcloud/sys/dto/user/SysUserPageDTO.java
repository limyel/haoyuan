package com.limyel.haoyuan.blogcloud.sys.dto.user;

import com.limyel.haoyuan.common.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserPageDTO extends PageParam {

    private String username;

    private Long deptId;

    private String mobile;

    private Integer status;

    private LocalDateTime[] createTime;

}