package com.limyel.haoyuan.blogcloud.sys.controller;

import com.limyel.haoyuan.blogcloud.sys.convert.MenuConvert;
import com.limyel.haoyuan.blogcloud.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.MenuEntity;
import com.limyel.haoyuan.blogcloud.sys.service.MenuService;
import com.limyel.haoyuan.blogcloud.sys.vo.menu.MenuVO;
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