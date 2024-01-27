package com.limyel.haoyuan.module.system.user.controller;

import com.limyel.haoyuan.module.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
@Validated
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;



}
