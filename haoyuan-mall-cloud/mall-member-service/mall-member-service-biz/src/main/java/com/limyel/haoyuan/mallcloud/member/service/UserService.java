package com.limyel.haoyuan.mallcloud.member.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.CryptUtil;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.common.member.constant.PaymentMethodEnum;
import com.limyel.haoyuan.mall.common.member.convert.UserConvert;
import com.limyel.haoyuan.mall.common.member.dto.user.UserDTO;
import com.limyel.haoyuan.mall.common.member.dto.user.UserPageDTO;
import com.limyel.haoyuan.mall.common.member.dto.user.api.MemberUserSecurity;
import com.limyel.haoyuan.mall.common.member.dto.user.api.PointBalanceChange;
import com.limyel.haoyuan.mall.common.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.common.member.entity.UserEntity;
import com.limyel.haoyuan.mall.common.member.vo.user.UserInfoVO;
import com.limyel.haoyuan.mall.common.member.vo.user.UserPageVO;
import com.limyel.haoyuan.mallcloud.member.dao.UserDao;
import com.limyel.haoyuan.mallcloud.common.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final RedissonClient redissonClient;

    public int create(UserDTO dto) {
        userDao.validateUnique(null, UserEntity::getUsername, dto.getUsername(), "用户名已存在");

        UserEntity user = UserConvert.INSTANCE.toEntity(dto);
        user.setPassword(CryptUtil.encrypt(dto.getPassword()));
        user.setPoint(0L);
        user.setBalance(0L);
        user.setStatus(StatusEnum.ENABLE.getValue());

        return userDao.insert(user);
    }

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

    public PageData<UserPageVO> getPage(UserPageDTO dto) {
        Page<UserEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<UserEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.likeIfPresent(UserEntity::getUsername, dto.getUsername());
        wrapperPlus.eqIfPresent(UserEntity::getStatus, dto.getStatus());
        userDao.selectPage(page, wrapperPlus);

        List<UserPageVO> list = page.getRecords().stream()
                .map(UserConvert.INSTANCE::toPageVO)
                .toList();
        return new PageData<>(page, list);
    }

    public UserEntity getById(Long id) {
        UserEntity user = userDao.selectOne(UserEntity::getId, id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return user;
    }

    public UserInfoVO getCurrentUserInfo() {
        Optional<Long> memberUserId = SecurityUtil.getMemberUserId();
        Long userId = memberUserId.orElseThrow(() -> new ServiceException("用户未登录"));

        UserEntity user = userDao.selectOne(UserEntity::getId, userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }

        return UserConvert.INSTANCE.toInfoVO(user);
    }

    public MemberUserSecurity getByUsername(String username) {
        UserEntity user = userDao.selectOne(UserEntity::getUsername, username);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return UserConvert.INSTANCE.toSecurity(user);
    }

    public MemberUserSecurity getByMobile(String mobile) {
        UserEntity user = userDao.selectOne(UserEntity::getMobile, mobile);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return UserConvert.INSTANCE.toSecurity(user);
    }

    public UserEntity getByBlogUsername(String blogUsername) {
        UserEntity result = userDao.selectOne(UserEntity::getBlogUsername, blogUsername);
        if (result == null) {
            throw new ServiceException("用户不存在");
        }
        return result;
    }

    /**
     * @param dto
     * @return
     */
    public Integer deductPointBalance(PointBalanceChange dto) {
        // 更新用户积分、余额的分布式锁
        RLock lock = redissonClient.getLock("deductPointLock:" + dto.getUserId());
        int result = 0;

        try {
            lock.lock();

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

            result = userDao.updateById(user);
            Assert.isTrue(result == 1, "积分、余额扣减失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }

        // todo 发送消息，记录 paylog
        PayLogEntity payLogEntity = new PayLogEntity();

        return result;
    }

}
