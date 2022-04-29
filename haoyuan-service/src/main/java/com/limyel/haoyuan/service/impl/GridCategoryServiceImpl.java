package com.limyel.haoyuan.service.impl;

import com.limyel.haoyuan.dao.GridCategoryMapper;
import com.limyel.haoyuan.entity.GridCategory;
import com.limyel.haoyuan.service.GridCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页分类 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class GridCategoryServiceImpl extends ServiceImpl<GridCategoryMapper, GridCategory> implements GridCategoryService {

    @Override
    public List<GridCategory> listAll() {
        return null;
    }
}
