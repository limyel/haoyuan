package com.limyel.haoyuan.mall.member.dto.level;

import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class LevelDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
    private Long id;

    @NotBlank(message = "等级名不能为空")
    private String name;

    @Min(value = 0, message = "需要的积分不能低于 0")
    private Long point;

    private Integer sort;

}
