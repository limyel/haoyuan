package com.limyel.haoyuan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUserDO {

    private Long id;

    private String username;

    private String password;

}
