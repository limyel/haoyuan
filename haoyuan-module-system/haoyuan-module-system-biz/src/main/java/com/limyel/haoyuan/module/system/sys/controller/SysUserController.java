package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.entity.SysUserDO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostPageDTO;
import com.limyel.haoyuan.module.system.sys.vo.post.PostVO;
import com.limyel.haoyuan.module.system.sys.dto.user.SysUserDTO;
import com.limyel.haoyuan.module.system.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/user")
@Validated
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    @PreAuthorize("hasPermission('sys:user:create')")
    public Result<Long> create(@RequestBody SysUserDTO dto) {
        Long id = sysUserService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    @PreAuthorize("hasPermission('sys:user:update')")
    public Result<?> update(@RequestBody SysUserDTO dto) {
        sysUserService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('sys:user:delete')")
    public Result<?> delete(@PathVariable("id") Long id) {
        sysUserService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('sys:user:get')")
    public Result<SysUserDO> get(@PathVariable("id") Long id) {
        SysUserDO sysUser = sysUserService.get(id);
        return Result.ok(sysUser);
    }

    @GetMapping
    public Result<PageData<PostVO>> getPage(PostPageDTO dto) {
//        PageData<SysPostDO> page = sysUserService.getPage(dto);
//        return Result.ok(new PageData<>(page, SysPostConvert.INSTANCE.toListVO(page.getList())));
        return null;
    }

}
