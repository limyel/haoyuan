package com.limyel.haoyuan.blog.member.dto.point;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PointChangeDTO {

    @NotNull
    private Long userId;

    @Min(value = 1, message = "积分必须是正数")
    private Integer point;

    @Range(min = 0, max = 1, message = "类型范围错误")
    private Integer type;

    private String reason;

}
