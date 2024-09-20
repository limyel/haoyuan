package com.limyel.haoyuan.mall.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.common.trade.convert.UserSpuConvert;
import com.limyel.haoyuan.mall.common.trade.dto.userspu.UseSpuDTO;
import com.limyel.haoyuan.mall.common.trade.entity.OrderItemEntity;
import com.limyel.haoyuan.mall.common.trade.entity.UserProductEntity;
import com.limyel.haoyuan.mall.common.trade.vo.userspu.UserProductVO;
import com.limyel.haoyuan.mall.trade.dao.UserSpuDao;
import com.limyel.haoyuan.mallcloud.common.security.util.SecurityUtil;
import com.limyel.haoyuan.mallcloud.product.constant.SpuTypeEnum;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserSpuDao userSpuDao;

    private final OrderItemService orderItemService;

    private final RedissonClient redissonClient;

    /**
     * @param userId
     * @param orderId
     * @return
     */
    public int createOrUpdate(Long userId, Long orderId) {
        int result = 0;

        List<OrderItemEntity> orderItems = orderItemService.getByOrderId(orderId);
        for (OrderItemEntity orderItem : orderItems) {
            // 更新用户拥有商品数量的分布式锁
            RLock lock = redissonClient.getLock("userProductLock:" + userId + orderItem.getSkuId());

            try {
                lock.lock();

                UserProductEntity userProduct = userSpuDao.selectOne(new LambdaQueryWrapper<UserProductEntity>()
                        .eq(UserProductEntity::getUserId, userId)
                        .eq(UserProductEntity::getSkuId, orderItem.getSkuId()));
                Integer quantity = null;
                LocalDateTime subscribeTime = null;
                LocalDateTime now = LocalDateTime.now();
                if (userProduct == null) {
                    userProduct = new UserProductEntity();
                    userProduct.setUserId(userId);
                    BeanUtils.copyProperties(orderItem, userProduct);
                    userProduct.setQuantity(null);
                }

                if (SpuTypeEnum.ONCE.getValue().equals(orderItem.getType())) {
                    quantity = userProduct.getQuantity() == null ? 0 : userProduct.getQuantity();
                    userProduct.setQuantity(quantity + orderItem.getQuantity());
                } else if (SpuTypeEnum.SUBSCRIBE.getValue().equals(orderItem.getType())) {
                    subscribeTime = userProduct.getSubscribeTime() == null || userProduct.getSubscribeTime().isBefore(now)
                            ? now : userProduct.getSubscribeTime();
                    userProduct.setSubscribeTime(subscribeTime.plusDays(userProduct.getQuantity() * 7L));
                }

                result += userSpuDao.insertOrUpdate(userProduct);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (lock != null) {
                    lock.unlock();
                }
            }
        }

        return result;
    }

    public List<UserProductVO> getByUserId(Long userId) {
        List<UserProductEntity> list = userSpuDao.selectList(new LambdaQueryWrapper<UserProductEntity>()
                .eq(UserProductEntity::getUserId, userId)
                .and(wrapper -> wrapper.ge(UserProductEntity::getQuantity, 0).or().ge(UserProductEntity::getSubscribeTime, LocalDateTime.now()))
        );
        return list.stream()
                .map(UserSpuConvert.INSTANCE::toVO)
                .toList();
    }

    public void useSpu(UseSpuDTO dto) {
        long userId = SecurityUtil.getMemberUserId().get();
        LambdaQueryWrapper<UserProductEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserProductEntity::getUserId, userId);
        wrapper.eq(UserProductEntity::getSkuId, dto.getSpuId());
        wrapper.eq(UserProductEntity::getType, SpuTypeEnum.ONCE.getValue());

        UserProductEntity userSpu = userSpuDao.selectOne(wrapper);
        if (userSpu == null) {
            throw new ServiceException();
        }

        if (userSpu.getQuantity() < dto.getQuantity()) {
            throw new ServiceException();
        }

        userSpu.setQuantity(userSpu.getQuantity() - dto.getQuantity());
        userSpuDao.updateById(userSpu);
    }
}
