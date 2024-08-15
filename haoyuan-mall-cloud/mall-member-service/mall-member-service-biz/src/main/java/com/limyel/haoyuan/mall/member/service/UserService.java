package com.limyel.haoyuan.mall.member.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.CryptUtil;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import com.limyel.haoyuan.mall.member.constant.PaymentMethodEnum;
import com.limyel.haoyuan.mall.member.convert.UserConvert;
import com.limyel.haoyuan.mall.member.dao.UserDao;
import com.limyel.haoyuan.mall.member.rdto.user.PointBalanceRDTO;
import com.limyel.haoyuan.mall.member.rdto.user.UserDTO;
import com.limyel.haoyuan.mall.member.rdto.user.UserInfoRDTO;
import com.limyel.haoyuan.mall.member.rdto.user.UserPageDTO;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.vo.user.UserInfoVO;
import com.limyel.haoyuan.mall.member.vo.user.UserPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

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

    public int updateStatus(Long id, Integer status) {
        LambdaUpdateWrapper<UserEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(UserEntity::getStatus, status);
        wrapper.eq(UserEntity::getId, id);

        return userDao.update(wrapper);
    }

    public int updateBlogAdminId(Long id, Long blogAdminId) {
        LambdaUpdateWrapper<UserEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(UserEntity::getBlogAdminId, blogAdminId);
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
        Long loginId = StpUserUtil.getLoginIdAsLong();
        UserEntity user = userDao.selectOne(UserEntity::getId, loginId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }

        return UserConvert.INSTANCE.toInfoVO(user);
    }

    public UserInfoRDTO getByUsername(String username) {
        UserEntity user = userDao.selectOne(UserEntity::getUsername, username);
        return UserConvert.INSTANCE.toInfoDTO(user);
    }

    public Integer deductPointBalance(PointBalanceRDTO dto) {
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
