package com.limyel.haoyuan.service.impl;

import com.limyel.haoyuan.dao.GridCategoryRepository;
import com.limyel.haoyuan.entity.GridCategory;
import com.limyel.haoyuan.service.GridCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryServiceImpl implements GridCategoryService {

    @Autowired
    private GridCategoryRepository gridCategoryRepository;


    @Override
    public List<GridCategory> list() {
        return null;
    }
}
