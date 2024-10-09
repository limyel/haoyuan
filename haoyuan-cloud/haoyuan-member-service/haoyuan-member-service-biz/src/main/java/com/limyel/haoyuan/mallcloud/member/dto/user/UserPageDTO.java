package com.limyel.haoyuan.mallcloud.member.dto.user;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageDTO extends PageParam {

    private String username;

    private Integer status;

}
