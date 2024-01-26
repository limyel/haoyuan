package com.limyel.haoyuan.module.sys.dept.controller;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.sys.dept.convert.SysPostConvert;
import com.limyel.haoyuan.module.sys.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.sys.dept.dto.SysPostFilterDTO;
import com.limyel.haoyuan.module.sys.dept.entity.SysPostEntity;
import com.limyel.haoyuan.module.sys.dept.service.SysPostService;
import com.limyel.haoyuan.module.sys.dept.vo.SysPostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Result<PageData<SysPostVO>> getPage(SysPostFilterDTO dto) {
        PageData<SysPostEntity> page = sysPostService.getPage(dto);
        return Result.ok(new PageData<>(page, SysPostConvert.INSTANCE.toListVO(page.getList())));
    }

}
