package com.limyel.haoyuan.api;


import com.limyel.haoyuan.common.api.Response;
import com.limyel.haoyuan.entity.GridCategory;
import com.limyel.haoyuan.service.CategoryService;
import com.limyel.haoyuan.service.GridCategoryService;
import com.limyel.haoyuan.vo.CategoryAllVO;
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
    public Response<CategoryAllVO> all() {
        return Response.success(categoryService.all());
    }

    @GetMapping("/grid")
    public Response<List<GridCategory>> listGrid() {
        List<GridCategory> gridCategories = gridCategoryService.list();
        return Response.success(gridCategories);
    }

}

