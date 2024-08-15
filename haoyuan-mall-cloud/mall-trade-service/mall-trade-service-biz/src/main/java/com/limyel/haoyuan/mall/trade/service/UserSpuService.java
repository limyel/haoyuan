package com.limyel.haoyuan.mall.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.mall.product.constant.SpuTypeEnum;
import com.limyel.haoyuan.mall.trade.convert.UserSpuConvert;
import com.limyel.haoyuan.mall.trade.dao.UserSpuDao;
import com.limyel.haoyuan.mall.trade.entity.OrderItemEntity;
import com.limyel.haoyuan.mall.trade.entity.UserSpuEntity;
import com.limyel.haoyuan.mall.trade.vo.userspu.UserSpuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSpuService {

    private final UserSpuDao userSpuDao;

    private final OrderItemService orderItemService;

    public int createOrUpdate(Long userId, Long orderId) {
        int result = 0;

        List<OrderItemEntity> orderItems = orderItemService.getByOrderId(orderId);
        for (OrderItemEntity orderItem : orderItems) {
            UserSpuEntity userSpu = userSpuDao.selectOne(new LambdaQueryWrapper<UserSpuEntity>()
                    .eq(UserSpuEntity::getUserId, userId)
                    .eq(UserSpuEntity::getSpuId, orderItem.getSpuId()));

            Integer quantity = null;
            LocalDateTime subscribeTime = null;
            LocalDateTime now = LocalDateTime.now();
            if (userSpu == null) {
                userSpu = new UserSpuEntity();
                userSpu.setUserId(userId);
                BeanUtils.copyProperties(orderItem, userSpu);
            }

            if (SpuTypeEnum.ONCE.getValue().equals(orderItem.getType())) {
                quantity = userSpu.getQuantity() == null ? 0 : userSpu.getQuantity();
                userSpu.setQuantity(quantity + orderItem.getQuantity());
            } else if (SpuTypeEnum.SUBSCRIBE.getValue().equals(orderItem.getType())) {
                subscribeTime = userSpu.getSubscribeTime() == null || userSpu.getSubscribeTime().isBefore(now)
                        ? now : userSpu.getSubscribeTime();
                userSpu.setSubscribeTime(subscribeTime.plusDays(userSpu.getQuantity() * 7L));
            }

            result += userSpuDao.insertOrUpdate(userSpu);
        }

        return result;
    }

    public List<UserSpuVO> getByUserId(Long userId) {
        List<UserSpuEntity> list = userSpuDao.selectList(new LambdaQueryWrapper<UserSpuEntity>()
                .eq(UserSpuEntity::getUserId, userId)
                .and(wrapper -> wrapper.ge(UserSpuEntity::getQuantity, 0).or().ge(UserSpuEntity::getSubscribeTime, LocalDateTime.now()))
        );
        return list.stream()
                .map(UserSpuConvert.INSTANCE::toVO)
                .toList();
    }
}
