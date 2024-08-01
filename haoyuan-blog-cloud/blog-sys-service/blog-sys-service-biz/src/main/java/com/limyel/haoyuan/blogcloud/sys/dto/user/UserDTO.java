package com.limyel.haoyuan.blogcloud.sys.dto.user;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private Integer status;

    private String remark;

}