package com.limyel.haoyuan.module.system.user.service.impl;

import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.dept.service.SysUserPostService;
import com.limyel.haoyuan.module.system.user.convert.SysUserConvert;
import com.limyel.haoyuan.module.system.user.dao.SysUserDao;
import com.limyel.haoyuan.module.system.user.dataobject.SysUserDO;
import com.limyel.haoyuan.module.system.user.dto.SysUserDTO;
import com.limyel.haoyuan.module.system.user.dto.SysUserFilterDTO;
import com.limyel.haoyuan.module.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserPostService sysUserPostService;

    @Override
    public Long create(SysUserDTO dto) {
        // todo 租户
        validateUsernameUnique(null, dto.getUsername());
        validateMobileUnique(null, dto.getMobile());
        validateEmailUnique(null, dto.getEmail());

        SysUserDO sysUser = SysUserConvert.INSTANCE.toDO(dto);
        // todo 密码加密
        sysUser.setPassword(dto.getPassword());
        sysUserDao.insert(sysUser);

        // 插入关联岗位
        sysUserPostService.createUserPosts(sysUser.getId(), dto.getPostIds());

        return sysUser.getId();
    }

    @Override
    public void update(SysUserDTO dto) {
        validateExist(dto.getId());
        validateUsernameUnique(dto.getId(), dto.getUsername());
        validateMobileUnique(dto.getId(), dto.getMobile());
        validateEmailUnique(dto.getId(), dto.getEmail());

        SysUserDO sysUser = SysUserConvert.INSTANCE.toDO(dto);
        // 此处不更新密码
        sysUser.setPassword(null);
        sysUserDao.updateById(sysUser);

        sysUserPostService.updateUserPosts(sysUser.getId(), dto.getPostIds());
    }

    @Override
    public void delete(Long id) {
        validateExist(id);

        sysUserDao.deleteById(id);

        // 删除关联数据
        sysUserPostService.deleteByUserId(id);
    }

    @Override
    public SysUserDO get(Long id) {
        return null;
    }

    @Override
    public List<SysUserDO> getList(SysUserFilterDTO dto) {
        return null;
    }

    @Override
    public PageData<SysUserDO> getPage(SysUserFilterDTO dto) {
        return null;
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        SysUserDO sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new BizException(SysErrorCodeConstant.USER_NOT_FOUND);
        }
    }

    private void validateUsernameUnique(Long id, String username) {
        SysUserDO sysUser = sysUserDao.selectByUsername(username);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new BizException(SysErrorCodeConstant.USER_USERNAME_DUPLICATE);
        }
    }

    private void validateMobileUnique(Long id, String mobile) {
        SysUserDO sysUser = sysUserDao.selectByMobile(mobile);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new BizException(SysErrorCodeConstant.USER_MOBILE_DUPLICATE);
        }
    }

    private void validateEmailUnique(Long id, String email) {
        SysUserDO sysUser = sysUserDao.selectByEmail(email);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new BizException(SysErrorCodeConstant.USER_EMAIL_DUPLICATE);
        }
    }

}
