package com.limyel.haoyuan.blog.store.service;

import com.limyel.haoyuan.blog.store.dao.UserProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserProductDao userProductDao;

    public int create() {
        return 0;
    }

    public int delete() {
        return 0;
    }

    public int updateQuantity() {
        return 0;
    }

    public int updateSubscribeTime() {
        return 0;
    }

    public List<?> getList() {
        return null;
    }

}
