package com.limyel.haoyuan.mall.common.member.dto.user.api;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class PointBalanceChange {

    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @NotNull(message = "总额不能为空")
    private Long total;

    @NotNull(message = "变更类型不能为空")
    @Range(min = 0, max = 2, message = "变更类型错误")
    private Integer type;

}
