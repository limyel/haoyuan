package com.limyel.haoyuan.common.core.pojo;

import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class BaseDTO {

    @NotNull(groups = Update.class, message = "不允许 ID 为空")
    @Null(groups = Create.class, message = "不允许指定 ID")
    private Long id;

}
