package com.limyel.haoyuan.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.convert.DeptConvert;
import com.limyel.haoyuan.system.entity.DeptEntity;
import com.limyel.haoyuan.system.dto.dept.DeptDTO;
import com.limyel.haoyuan.system.dto.dept.DeptPageDTO;
import com.limyel.haoyuan.system.service.DeptService;
import com.limyel.haoyuan.system.vo.dept.DeptVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    // todo validated
    @SaCheckPermission("sys:dept:create")
    @PostMapping("/create")
    public R<Long> create(@RequestBody DeptDTO dto) {
        Long id = deptService.create(dto);
        return R.ok(id);
    }

    @SaCheckPermission("sys:dept:delete")
    @GetMapping("/delete/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        deptService.delete(id);
        return new R<>();
    }

    @SaCheckPermission("sys:dept:update")
    @PostMapping("update")
    public R<?> update(@RequestBody DeptDTO dto) {
        deptService.update(dto);
        return new R<>();
    }

    @SaCheckPermission("sys:dept:get")
    @GetMapping("/get/{id}")
    public R<DeptVO> get(@PathVariable("id") Long id) {
        DeptEntity dept = deptService.get(id);
        return R.ok(DeptConvert.INSTANCE.toVO(dept));
    }

    @SaCheckPermission("sys:dept:list")
    @GetMapping("/page")
    public R<PageData<DeptVO>> getPage(DeptPageDTO dto) {
        PageData<DeptEntity> page = deptService.getPage(dto);
        return R.ok(new PageData<>(page, DeptConvert.INSTANCE.toListVO(page.getList())));
    }

}