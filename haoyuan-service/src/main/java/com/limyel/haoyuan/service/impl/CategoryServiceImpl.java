package com.limyel.haoyuan.service.impl;

import com.limyel.haoyuan.dao.CategoryRepository;
import com.limyel.haoyuan.entity.Category;
import com.limyel.haoyuan.service.CategoryService;
import com.limyel.haoyuan.vo.CategoryAllVO;
import com.limyel.haoyuan.vo.CategoryPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryAllVO all() {
        List<Category> rootCategories = categoryRepository.findCategoriesByRoot(true);
        List<Category> subCategories = categoryRepository.findCategoriesByRoot(false);
        CategoryAllVO result = new CategoryAllVO();
        result.setRoot(rootCategories.stream().map(CategoryPureVO::new).collect(Collectors.toList()));
        result.setSub(subCategories.stream().map(CategoryPureVO::new).collect(Collectors.toList()));
        return result;
    }
}
