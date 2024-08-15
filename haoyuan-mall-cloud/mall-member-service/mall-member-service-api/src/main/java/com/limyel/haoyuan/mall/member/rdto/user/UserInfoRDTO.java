package com.limyel.haoyuan.mall.member.rdto.user;

import lombok.Data;

@Data
public class UserInfoRDTO {

    private Long id;

    private String username;

    private String password;

    private Long point;

    private Integer status;

}
