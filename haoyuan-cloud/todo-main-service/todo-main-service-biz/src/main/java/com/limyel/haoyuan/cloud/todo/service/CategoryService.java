package com.limyel.haoyuan.cloud.todo.service;

import com.limyel.haoyuan.cloud.todo.dao.CategoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDao categoryDao;

}
