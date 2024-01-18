package com.limyel.haoyuan.module.system.dept.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.ServiceException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.dept.convert.SysPostConvert;
import com.limyel.haoyuan.module.system.dept.dao.SysPostDao;
import com.limyel.haoyuan.module.system.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.system.dept.dto.req.SysPostFilterReq;
import com.limyel.haoyuan.module.system.dept.entity.SysPostEntity;
import com.limyel.haoyuan.module.system.dept.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysPostServiceImpl implements SysPostService {

    @Autowired
    private SysPostDao sysPostDao;

    @Override
    public Long create(SysPostDTO dto) {
        validateNameUnique(null, dto.getName());
        validateCodeUnique(null, dto.getCode());

        SysPostEntity sysPost = SysPostConvert.INSTANCE.toEntity(dto);
        sysPostDao.insert(sysPost);

        return sysPost.getId();
    }

    @Override
    public void update(SysPostDTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateCodeUnique(dto.getId(), dto.getCode());

        SysPostEntity sysPost = SysPostConvert.INSTANCE.toEntity(dto);
        sysPostDao.updateById(sysPost);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        sysPostDao.deleteById(id);
    }

    @Override
    public SysPostEntity get(Long id) {
        return sysPostDao.selectById(id);
    }

    @Override
    public List<SysPostEntity> getList(SysPostFilterReq req) {
        return sysPostDao.selectList(req);
    }

    @Override
    public PageData<SysPostEntity> getPage(SysPostFilterReq req) {
        Page<SysPostEntity> page = new Page<>(req.getPageNum(), req.getPageSize());
        LambdaQueryWrapperPlus<SysPostEntity> wrapperPlus = new LambdaQueryWrapperPlus<SysPostEntity>()
                .likeIfPresent(SysPostEntity::getName, req.getName())
                .eqIfPresent(SysPostEntity::getStatus, req.getStatus())
                .orderByAsc(SysPostEntity::getSort);
        sysPostDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    private void validateNameUnique(Long id, String name) {
        SysPostEntity sysPost = sysPostDao.selectByNameAndCode(name, null);
        if (sysPost == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysPost.getId())) {
            throw new ServiceException(SysErrorCodeConstant.POST_NAME_DUPLICATE);
        }
    }

    private void validateCodeUnique(Long id, String code) {
        SysPostEntity sysPost = sysPostDao.selectByNameAndCode(null, code);
        if (sysPost == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysPost.getId())) {
            throw new ServiceException(SysErrorCodeConstant.POST_CODE_DUPLICATE);
        }
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        SysPostEntity sysPost = sysPostDao.selectById(id);
        if (sysPost == null) {
            throw new ServiceException(SysErrorCodeConstant.POST_NOT_FOUND);
        }
    }


}
