package com.limyel.haoyuan.mall.member.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.common.member.constant.PaymentMethodEnum;
import com.limyel.haoyuan.mall.common.member.dto.user.api.PointBalanceChange;
import com.limyel.haoyuan.mall.common.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public int delete(List<Long> ids) {
        return userDao.deleteBatchIds(ids);
    }

    public int update(UserEntity entity) {
        return userDao.updateById(entity);
    }

    public int updateStatus(Long id, Integer status) {
        LambdaUpdateWrapper<UserEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(UserEntity::getStatus, status);
        wrapper.eq(UserEntity::getId, id);

        return userDao.update(wrapper);
    }

    public UserEntity getById(Long id) {
        UserEntity user = userDao.selectOne(UserEntity::getId, id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return user;
    }

    public UserEntity getByUsername(String username) {
        UserEntity user = userDao.selectOne(UserEntity::getUsername, username);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return user;
    }

    public UserEntity getByMobild(String mobile) {
        UserEntity user = userDao.selectOne(UserEntity::getMobile, mobile);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return user;
    }

    /**
     * todo 分布式锁
     * @param dto
     * @return
     */
    public Integer deductPointBalance(PointBalanceChange dto) {
        Long userId = dto.getUserId();
        UserEntity user = userDao.selectById(userId);
        Long total = dto.getTotal();
        Integer type = dto.getType();

        if (PaymentMethodEnum.POINT.getValue().equals(type)) {
            if (user.getPoint() < total) {
                throw new ServiceException("用户积分不足");
            }
            user.setPoint(user.getPoint() - total);
        } else if (PaymentMethodEnum.BALANCE.getValue().equals(type)) {
            if (user.getBalance() < total) {
                throw new ServiceException("用户余额不足");
            }
            user.setBalance(user.getBalance() - total);
        } else if (PaymentMethodEnum.POINT_BALANCE.getValue().equals(type)) {
            if (user.getPoint() + user.getBalance() < total) {
                throw new ServiceException("用户积分、余额不足");
            }
            long delta = user.getPoint() - total;
            if (delta < 0) {
                user.setPoint(0L);
                user.setBalance(user.getBalance() + delta);
            } else {
                user.setPoint(delta);
            }
        }

        int result = userDao.updateById(user);
        Assert.isTrue(result == 1, "积分、余额扣减失败");

        // todo 发送消息，记录 paylog


        return result;
    }

}
