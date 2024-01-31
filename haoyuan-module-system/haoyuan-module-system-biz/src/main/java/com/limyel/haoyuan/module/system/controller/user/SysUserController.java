package com.limyel.haoyuan.module.system.controller.user;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.dto.dept.PostPageDTO;
import com.limyel.haoyuan.module.system.vo.dept.PostVO;
import com.limyel.haoyuan.module.system.dto.user.SysUserDTO;
import com.limyel.haoyuan.module.system.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/user")
@Validated
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public Result<Long> create(@RequestBody SysUserDTO dto) {
        Long id = sysUserService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody SysUserDTO dto) {
        sysUserService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        sysUserService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<PostVO> get(@PathVariable("id") Long id) {
//        SysPostDO sysPost = sysUserService.get(id);
//        return Result.ok(SysPostConvert.INSTANCE.toVO(sysPost));
        return null;
    }

    @GetMapping
    public Result<PageData<PostVO>> getPage(PostPageDTO dto) {
//        PageData<SysPostDO> page = sysUserService.getPage(dto);
//        return Result.ok(new PageData<>(page, SysPostConvert.INSTANCE.toListVO(page.getList())));
        return null;
    }

}
