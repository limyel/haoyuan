package com.limyel.haoyuan.mall.trade.service;

import com.limyel.haoyuan.mall.trade.dao.OrderItemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemDao orderItemDao;

    public List<OrderItemVO> getList(Long orderId) {

    }

}
