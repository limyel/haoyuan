package com.limyel.haoyuan.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("admin_user")
public class UserDO extends BaseDO {

    private String username;

    private String password;

}
