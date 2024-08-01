package com.limyel.haoyuan.blogcloud.sys.controller;

import com.limyel.haoyuan.blogcloud.sys.dto.user.SysUserDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.UserEntity;
import com.limyel.haoyuan.blogcloud.sys.service.SysUserService;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final SysUserService userService;

    @PostMapping("/create")
    public R<Long> create(@RequestBody SysUserDTO dto) {
        Long id = userService.create(dto);
        return R.ok(id);
    }

    @PostMapping("/update")
    public R<?> update(@RequestBody SysUserDTO dto) {
        userService.update(dto);
        return new R<>();
    }

    @GetMapping("/delete/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new R<>();
    }

    @GetMapping("/get/by/{id}")
    public R<UserEntity> getById(@PathVariable("id") Long id) {
        UserEntity sysUser = userService.get(id);
        return R.ok(sysUser);
    }

    @GetMapping("/get/page")
    public R<PageData<?>> getPage() {
        return null;
    }

}