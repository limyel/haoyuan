package com.limyel.haoyuan.module.system.dept.controller;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.dept.convert.SysDeptConvert;
import com.limyel.haoyuan.module.system.dept.dto.SysDeptDTO;
import com.limyel.haoyuan.module.system.dept.dto.SysDeptFilterDTO;
import com.limyel.haoyuan.module.system.dept.entity.SysDeptEntity;
import com.limyel.haoyuan.module.system.dept.service.SysDeptService;
import com.limyel.haoyuan.module.system.dept.vo.SysDeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    // todo validated
    @PostMapping
    public Result<Long> create(@RequestBody SysDeptDTO dto) {
        Long id = sysDeptService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody SysDeptDTO dto) {
        sysDeptService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        sysDeptService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<SysDeptVO> get(@PathVariable("id") Long id) {
        SysDeptEntity sysDept = sysDeptService.get(id);
        return Result.ok(SysDeptConvert.INSTANCE.toVO(sysDept));
    }

    @GetMapping
    public Result<PageData<SysDeptVO>> getPage(SysDeptFilterDTO dto) {
        PageData<SysDeptEntity> page = sysDeptService.getPage(dto);
        return Result.ok(new PageData<>(page, SysDeptConvert.INSTANCE.toListVO(page.getList())));
    }

}
