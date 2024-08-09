package com.limyel.haoyuan.mall.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import com.limyel.haoyuan.mall.product.api.SpuApi;
import com.limyel.haoyuan.mall.product.dto.SpuRpcDTO;
import com.limyel.haoyuan.mall.trade.constant.OrderRedisKey;
import com.limyel.haoyuan.mall.trade.convert.OrderConvert;
import com.limyel.haoyuan.mall.trade.dao.OrderDao;
import com.limyel.haoyuan.mall.trade.dto.order.OrderConfirmDTO;
import com.limyel.haoyuan.mall.trade.dto.order.OrderItemDTO;
import com.limyel.haoyuan.mall.trade.dto.order.OrderSubmitDTO;
import com.limyel.haoyuan.mall.trade.entity.OrderEntity;
import com.limyel.haoyuan.mall.trade.vo.order.OrderConfirmVO;
import com.limyel.haoyuan.mall.trade.vo.order.OrderListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    private final OrderItemService orderItemService;

    private final SpuApi spuApi;

    private final StringRedisTemplate redisTemplate;

    public PageData<OrderListVO> getList(PageParam pageParam) {
        Page<OrderEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        Long loginId = (Long) StpUserUtil.getLoginId();
        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderEntity::getUserId, loginId);

        orderDao.selectPage(page, wrapper);

        List<OrderListVO> list = page.getRecords().stream()
                .map(item -> {
                    OrderListVO result = OrderConvert.INSTANCE.toListVO(item);
                    result.setOrderItems(orderItemService.getList(result.getId()));
                    return result;
                })
                .toList();
        return new PageData<>(page, list);
    }

    public OrderConfirmVO confirm(OrderConfirmDTO dto) {
        OrderConfirmVO result = new OrderConfirmVO();

        // 获取订单商品
        for (OrderConfirmDTO.CartItem item : dto.getList()) {
            SpuRpcDTO spu = spuApi.getById(item.getSpuId());
            OrderItemDTO orderItem = new OrderItemDTO();
            orderItem.setSpuId(spu.getId());
            orderItem.setSpuName(spu.getName());
            orderItem.setPicUrl(spu.getPicUrl());
            orderItem.setPrice(spu.getPrice());
            orderItem.setQuantity(item.getNum());

            result.getOrderItems().add(orderItem);
        }

        // 创建订单编号
        String orderToken = UUID.randomUUID().toString().replace("-", "");
        result.setOrderToken(orderToken);
        redisTemplate.opsForValue().set(OrderRedisKey.ORDER_TOKEN_PREFIX + orderToken, orderToken);

        return result;
    }

    public void submit(OrderSubmitDTO dto) {
        // 判断订单是否重复提交
        // todo 用 lua 脚本保证原子性
        String orderToken = dto.getOrderToken();
        redisTemplate.opsForValue().get(OrderRedisKey.ORDER_TOKEN_PREFIX + orderToken);

        // 订单商品校验，确认商品价格、状态


        // 校验库存并锁定库存



    }


}
