package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.SysUserConvert;
import com.limyel.haoyuan.system.dao.SysUserDao;
import com.limyel.haoyuan.system.domain.SysUserEntity;
import com.limyel.haoyuan.system.dto.user.SysUserDTO;
import com.limyel.haoyuan.system.dto.user.SysUserPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    private final SysUserPostService sysUserPostService;

    public Long create(SysUserDTO dto) {
        // todo 租户
        validateUsernameUnique(null, dto.getUsername());
        validateMobileUnique(null, dto.getMobile());
        validateEmailUnique(null, dto.getEmail());

        SysUserEntity sysUser = SysUserConvert.INSTANCE.toDO(dto);
        // todo 密码加密
        sysUser.setPassword(sysUser.getPassword());
        sysUserDao.insert(sysUser);

        // 插入关联岗位
        if (!CollectionUtils.isEmpty(dto.getPostIds())) {
            sysUserPostService.createUserPosts(sysUser.getId(), dto.getPostIds());
        }

        return sysUser.getId();
    }

    public void update(SysUserDTO dto) {
        validateExist(dto.getId(), null);
        validateUsernameUnique(dto.getId(), dto.getUsername());
        validateMobileUnique(dto.getId(), dto.getMobile());
        validateEmailUnique(dto.getId(), dto.getEmail());

        SysUserEntity sysUser = SysUserConvert.INSTANCE.toDO(dto);
        // 此处不更新密码
        sysUser.setPassword(null);
        sysUserDao.updateById(sysUser);

        sysUserPostService.updateUserPosts(sysUser.getId(), dto.getPostIds());
    }

    public void delete(Long id) {
        validateExist(id, null);

        sysUserDao.deleteById(id);

        // 删除关联数据
        sysUserPostService.deleteByUserId(id);
    }

    public SysUserEntity get(Long id) {
        return sysUserDao.selectById(id);
    }

    public List<SysUserEntity> getList(SysUserPageDTO dto) {
        return null;
    }

    public PageData<SysUserEntity> getPage(SysUserPageDTO dto) {
        return null;
    }

    public SysUserEntity getByUsername(String username) {
        SysUserEntity sysUser = sysUserDao.selectByUsername(username);
        if (sysUser == null) {
            throw new BizException(SysErrorCode.USER_NOT_FOUND);
        }
        return sysUser;
    }

    // ----- 校验方法 -----

    public void validateExist(Long id, String username) {
        if (id == null && !StringUtils.hasText(username)) {
            return;
        }

        LambdaQueryWrapperPlus<SysUserEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(SysUserEntity::getId, id);
        wrapperPlus.eqIfPresent(SysUserEntity::getUsername, username);
        SysUserEntity sysUser = sysUserDao.selectOne(wrapperPlus);

        if (sysUser == null) {
            throw new BizException(SysErrorCode.USER_NOT_FOUND);
        }
    }

    public void validateUsernameUnique(Long id, String username) {
        SysUserEntity sysUser = sysUserDao.selectByUsername(username);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new BizException(SysErrorCode.USER_USERNAME_DUPLICATE);
        }
    }

    public void validateMobileUnique(Long id, String mobile) {
        SysUserEntity sysUser = sysUserDao.selectByMobile(mobile);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new BizException(SysErrorCode.USER_MOBILE_DUPLICATE);
        }
    }

    public void validateEmailUnique(Long id, String email) {
        SysUserEntity sysUser = sysUserDao.selectByEmail(email);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new BizException(SysErrorCode.USER_EMAIL_DUPLICATE);
        }
    }
}