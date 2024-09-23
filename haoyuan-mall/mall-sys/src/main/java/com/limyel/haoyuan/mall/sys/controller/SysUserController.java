package com.limyel.haoyuan.mall.sys.controller;

import com.limyel.haoyuan.mall.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys-user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/create")
    public void create() {

    }

    @GetMapping("/delete/by-ids/{ids}")
    public void deleteByIds(@PathVariable("ids") String ids) {

    }

    @PostMapping("/update")
    public void update() {

    }

    @GetMapping("/get/page")
    public void getPage() {

    }

    @GetMapping("/get/by-id/{id}")
    public void getById(@PathVariable("id") Long id) {

    }


}
