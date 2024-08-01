package com.limyel.haoyuan.blogcloud.sys.controller;

import com.limyel.haoyuan.blogcloud.sys.convert.RoleConvert;
import com.limyel.haoyuan.blogcloud.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.RoleEntity;
import com.limyel.haoyuan.blogcloud.sys.service.RoleService;
import com.limyel.haoyuan.blogcloud.sys.vo.role.RoleVO;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public R<Long> create(@RequestBody RoleDTO dto) {
        Long id = roleService.create(dto);
        return R.ok(id);
    }

    @PutMapping
    public R<?> update(@RequestBody RoleDTO dto) {
        roleService.update(dto);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return new R<>();
    }

    @GetMapping("/{id}")
    public R<RoleVO> get(@PathVariable("id") Long id) {
        RoleEntity role = roleService.get(id);
        return R.ok(RoleConvert.INSTANCE.toVO(role));
    }

}