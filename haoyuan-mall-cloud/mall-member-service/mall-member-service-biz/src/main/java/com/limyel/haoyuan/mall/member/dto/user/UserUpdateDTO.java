package com.limyel.haoyuan.mall.member.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateDTO {

    @NotNull(message = "用户ID不能为空")
    private Long id;

    @NotNull(groups = UpdateStatus.class, message = "状态不能为空")
    @Range(min = 0, max = 1, message = "状态范围异常")
    private Integer status;

    private Long blogAdminId;

}
