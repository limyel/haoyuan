package com.limyel.haoyuan.cloud.sys.dto;

import com.limyel.haoyuan.common.core.pojo.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends BaseDTO {

    /**
     * 角色名
     */
    @NotBlank(message = "不允许角色名为空")
    private String name;

    /**
     * 角色编码
     */
    @NotBlank(message = "不允许角色编码为空")
    private String code;

    /**
     * 显示顺序
     */
    @Min(value = 0, message = "不允许显示顺序小于 0")
    private Integer sort;

    /**
     * 状态，0-禁用，1-正常
     */
    @Range(min = 0, max = 1, message = "角色状态范围错误")
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
