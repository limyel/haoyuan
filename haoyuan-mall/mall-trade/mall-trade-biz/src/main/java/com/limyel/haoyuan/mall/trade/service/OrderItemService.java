package com.limyel.haoyuan.mall.trade.service;

import com.limyel.haoyuan.mall.trade.dao.OrderItemDao;
import com.limyel.haoyuan.mall.trade.dto.order.OrderItemDTO;
import com.limyel.haoyuan.mall.trade.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemDao orderItemDao;

    public int create(Long orderId, List<OrderItemDTO> orderItems) {
        List<OrderItemEntity> list = new ArrayList<>();
        for (OrderItemDTO item : orderItems) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrderId(orderId);
            BeanUtils.copyProperties(item, orderItem);
            orderItem.setTotalAmount(item.getPrice() * item.getQuantity());

            list.add(orderItem);
        }
        return orderItemDao.insertBatchSomeColumn(list);
    }

    public List<OrderItemDTO> getList(Long orderId) {
        return null;
    }

    public List<OrderItemEntity> getByOrderId(Long orderId) {
        return orderItemDao.selectList(OrderItemEntity::getOrderId, orderId);
    }

}
