package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.entity.SysUserEntity;
import com.limyel.haoyuan.system.dto.post.PostPageDTO;
import com.limyel.haoyuan.system.dto.user.SysUserDTO;
import com.limyel.haoyuan.system.service.SysUserService;
import com.limyel.haoyuan.system.vo.post.PostVO;
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
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/create")
    public R<Long> create(@RequestBody SysUserDTO dto) {
        Long id = sysUserService.create(dto);
        return R.ok(id);
    }

    @PostMapping("/update")
    public R<?> update(@RequestBody SysUserDTO dto) {
        sysUserService.update(dto);
        return new R<>();
    }

    @GetMapping("/delete/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        sysUserService.delete(id);
        return new R<>();
    }

    @GetMapping("/get/{id}")
    public R<SysUserEntity> get(@PathVariable("id") Long id) {
        SysUserEntity sysUser = sysUserService.get(id);
        return R.ok(sysUser);
    }

    @GetMapping("/page")
    public R<PageData<PostVO>> getPage(PostPageDTO dto) {
//        PageData<SysPostDO> page = sysUserService.getPage(dto);
//        return Result.ok(new PageData<>(page, SysPostConvert.INSTANCE.toListVO(page.getList())));
        return null;
    }

}