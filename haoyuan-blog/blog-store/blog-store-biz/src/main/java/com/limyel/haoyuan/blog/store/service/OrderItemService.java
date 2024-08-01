package com.limyel.haoyuan.blog.store.service;

import com.limyel.haoyuan.blog.store.dao.OrderItemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemDao orderItemDao;

    public int create() {
        return 0;
    }

    public int delete() {
        return 0;
    }

    public List<?> getByOrderId(Long orderId) {
        return null;
    }

}
