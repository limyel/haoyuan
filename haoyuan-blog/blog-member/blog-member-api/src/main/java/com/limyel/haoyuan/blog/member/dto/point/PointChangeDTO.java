package com.limyel.haoyuan.blog.member.dto.point;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PointChangeDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Min(value = 1, message = "积分必须是正数")
    private Integer point;

    private String reason;

}
