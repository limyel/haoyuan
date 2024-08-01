package com.limyel.haoyuan.blog.member.dto.point;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PointCreateDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "积分不能为空")
    private Integer point;

}
