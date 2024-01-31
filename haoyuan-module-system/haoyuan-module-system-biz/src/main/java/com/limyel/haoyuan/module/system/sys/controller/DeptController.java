package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.DeptConvert;
import com.limyel.haoyuan.module.system.sys.dto.dept.DeptDTO;
import com.limyel.haoyuan.module.system.sys.dto.dept.DeptPageDTO;
import com.limyel.haoyuan.module.system.sys.dataobject.DeptDO;
import com.limyel.haoyuan.module.system.sys.service.DeptService;
import com.limyel.haoyuan.module.system.sys.vo.dept.DeptVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "部门")
@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    // todo validated
    @PostMapping
    public Result<Long> create(@RequestBody DeptDTO dto) {
        Long id = deptService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody DeptDTO dto) {
        deptService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        deptService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<DeptVO> get(@PathVariable("id") Long id) {
        DeptDO dept = deptService.get(id);
        return Result.ok(DeptConvert.INSTANCE.toVO(dept));
    }

    @GetMapping
    public Result<PageData<DeptVO>> getPage(DeptPageDTO dto) {
        PageData<DeptDO> page = deptService.getPage(dto);
        return Result.ok(new PageData<>(page, DeptConvert.INSTANCE.toListVO(page.getList())));
    }

}
