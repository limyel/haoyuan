package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.convert.MenuConvert;
import com.limyel.haoyuan.system.entity.MenuEntity;
import com.limyel.haoyuan.system.dto.menu.MenuDTO;
import com.limyel.haoyuan.system.service.MenuService;
import com.limyel.haoyuan.system.vo.menu.MenuVO;
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
@RequestMapping("/sys/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public R<Long> create(@RequestBody MenuDTO dto) {
        Long id = menuService.create(dto);
        return R.ok(id);
    }

    @PutMapping
    public R<?> update(@RequestBody MenuDTO dto) {
        menuService.update(dto);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        menuService.delete(id);
        return new R<>();
    }

    @GetMapping("/{id}")
    public R<MenuVO> get(@PathVariable("id") Long id) {
        MenuEntity menu = menuService.get(id);
        return R.ok(MenuConvert.INSTANCE.toVO(menu));
    }

}