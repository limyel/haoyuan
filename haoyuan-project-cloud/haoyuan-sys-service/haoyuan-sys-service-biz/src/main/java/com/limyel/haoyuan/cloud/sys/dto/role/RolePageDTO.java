package com.limyel.haoyuan.cloud.sys.dto.role;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema
public class RolePageDTO extends PageParam {

    @Schema(title = "角色名")
    private String name;

    @Schema(title = "角色编码")
    private String code;

    @Range(min = 0, max = 1, message = "角色状态范围错误")
    @Schema(title = "角色状态")
    private Integer status;

}
