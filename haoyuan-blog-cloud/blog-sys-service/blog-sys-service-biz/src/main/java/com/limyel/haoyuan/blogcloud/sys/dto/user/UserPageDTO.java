package com.limyel.haoyuan.blogcloud.sys.dto.user;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageDTO extends PageParam {

    private String username;

    private Integer status;

    private LocalDateTime[] createTime;

}