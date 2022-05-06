package com.limyel.haoyuan.portal.controller;


import com.limyel.haoyuan.common.api.R;
import com.limyel.haoyuan.portal.entity.GridCategory;
import com.limyel.haoyuan.portal.service.CategoryService;
import com.limyel.haoyuan.portal.service.GridCategoryService;
import com.limyel.haoyuan.portal.vo.CategoryAllVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Category", description = "分类接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GridCategoryService gridCategoryService;

    @Operation(summary = "获取所有分类", method = "GET")
    @GetMapping
    public R<CategoryAllVO> all() {
        return R.success(categoryService.all());
    }

    @Operation(summary = "获取首页网格的分类", method = "GET")
    @GetMapping("/grid")
    public R<List<GridCategory>> listGrid() {
        List<GridCategory> gridCategories = gridCategoryService.list();
        return R.success(gridCategories);
    }

}

