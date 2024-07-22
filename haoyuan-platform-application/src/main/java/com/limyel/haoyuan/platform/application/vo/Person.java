package com.limyel.haoyuan.platform.application.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Person {

    private String name;

    private Date birthday;

    private LocalDateTime createTime;

}
