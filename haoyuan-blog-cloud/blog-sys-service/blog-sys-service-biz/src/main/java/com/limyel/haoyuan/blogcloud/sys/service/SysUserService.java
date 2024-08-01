package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.convert.UserConvert;
import com.limyel.haoyuan.blogcloud.sys.dao.UserDao;
import com.limyel.haoyuan.blogcloud.sys.dto.user.UserDTO;
import com.limyel.haoyuan.blogcloud.sys.dto.user.UserPageDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.UserEntity;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final UserDao userDao;

    public Long create(UserDTO dto) {
        validateUsernameUnique(null, dto.getUsername());

        UserEntity sysUser = UserConvert.INSTANCE.toEntity(dto);
        // todo 密码加密
        sysUser.setPassword(sysUser.getPassword());
        userDao.insert(sysUser);

        return sysUser.getId();
    }

    public void update(UserDTO dto) {
        validateExist(dto.getId(), null);
        validateUsernameUnique(dto.getId(), dto.getUsername());

        UserEntity sysUser = UserConvert.INSTANCE.toEntity(dto);
        // 此处不更新密码
        sysUser.setPassword(null);
        userDao.updateById(sysUser);
    }

    public void delete(Long id) {
        validateExist(id, null);

        userDao.deleteById(id);
    }

    public UserEntity get(Long id) {
        return userDao.selectById(id);
    }

    public List<UserEntity> getList(UserPageDTO dto) {
        return null;
    }

    public PageData<UserEntity> getPage(UserPageDTO dto) {
        return null;
    }

    public UserEntity getByUsername(String username) {
        UserEntity sysUser = userDao.selectByUsername(username);
        if (sysUser == null) {
            throw new ServiceException();
        }
        return sysUser;
    }

    // ----- 校验方法 -----

    public void validateExist(Long id, String username) {
        if (id == null && !StringUtils.hasText(username)) {
            return;
        }

        LambdaQueryWrapperPlus<UserEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(UserEntity::getId, id);
        wrapperPlus.eqIfPresent(UserEntity::getUsername, username);
        UserEntity sysUser = userDao.selectOne(wrapperPlus);

        if (sysUser == null) {
            throw new ServiceException();
        }
    }

    public void validateUsernameUnique(Long id, String username) {
        UserEntity sysUser = userDao.selectByUsername(username);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new ServiceException();
        }
    }

}