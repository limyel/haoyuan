package com.limyel.haoyuan.blog.store.service;

import com.limyel.haoyuan.blog.store.dao.ProductDao;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public int create() {
        return 0;
    }

    public int delete() {
        return 0;
    }

    public int update() {
        return 0;
    }

    public void getById() {

    }

    public PageData<?> getPage() {
        return null;
    }

    public List<?> getList() {
        return null;
    }

    public void updateStock() {

    }

    public void updateStatus() {

    }

}
