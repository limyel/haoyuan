package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.MenuConvert;
import com.limyel.haoyuan.module.system.sys.dataobject.MenuDO;
import com.limyel.haoyuan.module.system.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.module.system.sys.service.MenuService;
import com.limyel.haoyuan.module.system.sys.vo.menu.MenuVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "字典")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    @PreAuthorize("hasPermission('sys:menu:create')")
    public Result<Long> create(@RequestBody MenuDTO dto) {
        Long id = menuService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    @PreAuthorize("hasPermission('sys:menu:update')")
    public Result<?> update(@RequestBody MenuDTO dto) {
        menuService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('sys:menu:delete')")
    public Result<?> delete(@PathVariable("id") Long id) {
        menuService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('sys:menu:get')")
    public Result<MenuVO> get(@PathVariable("id") Long id) {
        MenuDO menu = menuService.get(id);
        return Result.ok(MenuConvert.INSTANCE.toVO(menu));
    }

}
