package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.DictDataConvert;
import com.limyel.haoyuan.module.system.sys.convert.RoleConvert;
import com.limyel.haoyuan.module.system.sys.dataobject.DictDataDO;
import com.limyel.haoyuan.module.system.sys.dataobject.RoleDO;
import com.limyel.haoyuan.module.system.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.module.system.sys.service.RoleService;
import com.limyel.haoyuan.module.system.sys.vo.dict.data.DictDataVO;
import com.limyel.haoyuan.module.system.sys.vo.role.RoleVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "字典")
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Result<Long> create(@RequestBody RoleDTO dto) {
        Long id = roleService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody RoleDTO dto) {
        roleService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<RoleVO> get(@PathVariable("id") Long id) {
        RoleDO role = roleService.get(id);
        return Result.ok(RoleConvert.INSTANCE.toVO(role));
    }

}
