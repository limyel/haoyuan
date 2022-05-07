package com.limyel.haoyuan.service.impl;

import com.limyel.haoyuan.dao.OrderMapper;
import com.limyel.haoyuan.model.entity.Order;
import com.limyel.haoyuan.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
