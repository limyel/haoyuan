package com.limyel.haoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.dao.CategoryMapper;
import com.limyel.haoyuan.model.entity.Category;
import com.limyel.haoyuan.model.vo.CategoryAllVO;
import com.limyel.haoyuan.model.vo.CategoryPureVO;
import com.limyel.haoyuan.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryAllVO all() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getRoot, true);
        List<Category> rootList = categoryMapper.selectList(queryWrapper);
        queryWrapper.clear();
        queryWrapper.eq(Category::getRoot, false);
        List<Category> subList = categoryMapper.selectList(queryWrapper);
        CategoryAllVO result = new CategoryAllVO();
        result.setRoot(rootList.stream().map(CategoryPureVO::new).collect(Collectors.toList()));
        result.setSub(subList.stream().map(CategoryPureVO::new).collect(Collectors.toList()));
        return result;
    }

}
