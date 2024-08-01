package com.limyel.haoyuan.blog.store.service;

import com.limyel.haoyuan.blog.store.dao.OrderDao;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    public int create() {
        return 0;
    }

    public int delete(Long userId) {
        return 0;
    }

    public void getBySn() {

    }

    public PageData<?> getPage() {
        return null;
    }

    public PageData<?> getList() {
        return null;
    }

    // todo 支付也放在提交订单这里
    public int submit() {
        return 0;
    }

    public void close() {

    }



}
