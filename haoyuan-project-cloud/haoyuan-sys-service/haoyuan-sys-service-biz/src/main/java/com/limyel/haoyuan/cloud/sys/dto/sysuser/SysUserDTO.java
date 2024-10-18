package com.limyel.haoyuan.cloud.sys.dto.sysuser;

import com.limyel.haoyuan.common.core.pojo.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDTO extends BaseDTO {

    private Long deptId;

    @NotBlank(message = "不允许用户名为空")
    private String username;

    @NotBlank(message = "不允许姓名为空")
    private String realname;

    @Length(min = 11, max = 11, message = "不允许错误的手机号")
    private String mobile;

    @Email(message = "不允许错误的邮箱格式")
    private String email;

    @NotBlank(message = "不允许密码为空")
    @Length(min = 6, message = "不允许密码长度小于 6 位")
    private String password;

    @NotNull(message = "不允许角色为空")
    private Long roleId;

    @NotNull(message = "不允许状态为空")
    @Range(min = 0, max = 1, message = "不允许错误的状态值")
    private Integer status;

    private String remark;

}
