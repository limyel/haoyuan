package com.limyel.haoyuan.module.system.dept.controller;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.dept.convert.SysPostConvert;
import com.limyel.haoyuan.module.system.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.system.dept.dto.req.SysPostFilterReq;
import com.limyel.haoyuan.module.system.dept.entity.SysPostEntity;
import com.limyel.haoyuan.module.system.dept.service.SysPostService;
import com.limyel.haoyuan.module.system.dept.vo.SysPostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/post")
@Validated
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;

    // todo validated
    @PostMapping
    public Result<Long> create(@RequestBody SysPostDTO dto) {
        Long id = sysPostService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody SysPostDTO dto) {
        sysPostService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        sysPostService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<SysPostVO> get(@PathVariable("id") Long id) {
        SysPostEntity sysPost = sysPostService.get(id);
        return Result.ok(SysPostConvert.INSTANCE.toVO(sysPost));
    }

    @GetMapping
    public Result<PageData<SysPostVO>> getPage(SysPostFilterReq req) {
        PageData<SysPostEntity> page = sysPostService.getPage(req);
        return Result.ok(new PageData<>(page, SysPostConvert.INSTANCE.toListVO(page.getList())));
    }

}
