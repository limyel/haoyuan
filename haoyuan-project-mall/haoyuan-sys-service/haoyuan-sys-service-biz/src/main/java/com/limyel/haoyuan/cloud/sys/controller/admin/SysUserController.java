package com.limyel.haoyuan.cloud.sys.controller.admin;

import com.limyel.haoyuan.cloud.sys.dto.sysuser.SysUserDTO;
import com.limyel.haoyuan.cloud.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys-user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/create")
    public void create(@Validated @RequestBody SysUserDTO dto) {

    }

    @GetMapping("/delete/by-ids/{ids}")
    public void deleteByIds(@PathVariable("ids") String ids) {

    }

    @PostMapping("/update")
    public void update() {

    }

    @GetMapping("/get-page")
    public void getPage() {

    }

    @GetMapping("/get/{id}")
    public void getById(@PathVariable("id") Long id) {

    }


}
