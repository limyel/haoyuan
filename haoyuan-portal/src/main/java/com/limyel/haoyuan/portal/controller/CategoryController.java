package com.limyel.haoyuan.portal.controller;


import com.limyel.haoyuan.common.api.R;
import com.limyel.haoyuan.portal.entity.GridCategory;
import com.limyel.haoyuan.portal.service.CategoryService;
import com.limyel.haoyuan.portal.service.GridCategoryService;
import com.limyel.haoyuan.portal.vo.CategoryAllVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 分类 前端控制器
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GridCategoryService gridCategoryService;

    @GetMapping
    public R<CategoryAllVO> all() {
        return R.success(categoryService.all());
    }

    @GetMapping("/grid")
    public R<List<GridCategory>> listGrid() {
        List<GridCategory> gridCategories = gridCategoryService.list();
        return R.success(gridCategories);
    }

}

