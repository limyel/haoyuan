package com.limyel.haoyuan.mall.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.product.constant.SpuTypeEnum;
import com.limyel.haoyuan.mall.security.entity.LoginUser;
import com.limyel.haoyuan.mall.trade.convert.UserSpuConvert;
import com.limyel.haoyuan.mall.trade.dao.UserProductDao;
import com.limyel.haoyuan.mall.trade.dto.userspu.UseProductDTO;
import com.limyel.haoyuan.mall.trade.entity.OrderItemEntity;
import com.limyel.haoyuan.mall.trade.entity.UserProductEntity;
import com.limyel.haoyuan.mall.trade.vo.userspu.UserProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserProductDao userProductDao;

    private final OrderItemService orderItemService;

    /**
     * todo 分布式锁
     * @param userId
     * @param orderId
     * @return
     */
    public int createOrUpdate(Long userId, Long orderId) {
        int result = 0;

        List<OrderItemEntity> orderItems = orderItemService.getByOrderId(orderId);
        for (OrderItemEntity orderItem : orderItems) {
            UserProductEntity userProduct = userProductDao.selectOne(new LambdaQueryWrapper<UserProductEntity>()
                    .eq(UserProductEntity::getUserId, userId)
                    .eq(UserProductEntity::getSkuId, orderItem.getSkuId()));

            LocalDateTime subscribeTime = null;
            LocalDateTime now = LocalDateTime.now();
            if (userProduct == null) {
                userProduct = new UserProductEntity();
                userProduct.setUserId(userId);
                BeanUtils.copyProperties(orderItem, userProduct);
                userProduct.setQuantity(0);
            }

            if (SpuTypeEnum.ONCE.getValue().equals(orderItem.getType())) {
                userProduct.setQuantity(userProduct.getQuantity() + orderItem.getQuantity());
            } else if (SpuTypeEnum.SUBSCRIBE.getValue().equals(orderItem.getType())) {
                subscribeTime = userProduct.getSubscribeTime() == null || userProduct.getSubscribeTime().isBefore(now)
                        ? now : userProduct.getSubscribeTime();
                userProduct.setSubscribeTime(subscribeTime.plusDays(orderItem.getQuantity() * 7L));
            }

            result += userProductDao.insertOrUpdate(userProduct);
        }

        return result;
    }

    public List<UserProductVO> getList() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserProductEntity> list = userProductDao.selectList(new LambdaQueryWrapper<UserProductEntity>()
                .eq(UserProductEntity::getUserId, loginUser.getId())
                .and(wrapper -> wrapper.ge(UserProductEntity::getQuantity, 0).or().ge(UserProductEntity::getSubscribeTime, LocalDateTime.now()))
        );
        return list.stream()
                .map(UserSpuConvert.INSTANCE::toVO)
                .toList();
    }

    public void useProduct(UseProductDTO dto) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loginUser.getId();
        LambdaQueryWrapper<UserProductEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserProductEntity::getUserId, userId);
        wrapper.eq(UserProductEntity::getSkuId, dto.getSkuId());
        wrapper.eq(UserProductEntity::getType, SpuTypeEnum.ONCE.getValue());

        UserProductEntity userProduct = userProductDao.selectOne(wrapper);
        if (userProduct == null) {
            throw new ServiceException();
        }

        if (userProduct.getQuantity() < dto.getQuantity()) {
            throw new ServiceException();
        }

        userProduct.setQuantity(userProduct.getQuantity() - dto.getQuantity());
        userProductDao.updateById(userProduct);
    }
}
