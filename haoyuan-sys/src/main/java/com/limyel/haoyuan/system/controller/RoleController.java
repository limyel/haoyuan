package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.convert.RoleConvert;
import com.limyel.haoyuan.system.domain.RoleEntity;
import com.limyel.haoyuan.system.dto.role.RoleDTO;
import com.limyel.haoyuan.system.service.RoleService;
import com.limyel.haoyuan.system.vo.role.RoleVO;
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