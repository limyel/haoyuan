package com.limyel.haoyuan.cloud.member.dto;

import lombok.Data;

@Data
public class MemberUserSecurity {

    private Long id;

    private String username;

    private String password;

    private Long point;

    private Integer status;

}
