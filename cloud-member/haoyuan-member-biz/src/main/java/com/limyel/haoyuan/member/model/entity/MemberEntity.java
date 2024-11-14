package com.limyel.haoyuan.member.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("haoyuan_member")
public class MemberEntity extends BaseEntity {

    private String username;

    private String password;

    private String avatar;

    private Long point;

    private Long balance;

    private Integer status;

}
