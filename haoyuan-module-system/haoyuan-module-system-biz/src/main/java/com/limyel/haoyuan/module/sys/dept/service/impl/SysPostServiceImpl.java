package com.limyel.haoyuan.module.sys.dept.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.ServiceException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.sys.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.sys.dept.convert.SysPostConvert;
import com.limyel.haoyuan.module.sys.dept.dao.SysPostDao;
import com.limyel.haoyuan.module.sys.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.sys.dept.dto.SysPostFilterDTO;
import com.limyel.haoyuan.module.sys.dept.entity.SysPostEntity;
import com.limyel.haoyuan.module.sys.dept.service.SysPostService;
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
    public List<SysPostEntity> getList(SysPostFilterDTO dto) {
        return sysPostDao.selectList(dto);
    }

    @Override
    public PageData<SysPostEntity> getPage(SysPostFilterDTO dto) {
        Page<SysPostEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<SysPostEntity> wrapperPlus = new LambdaQueryWrapperPlus<SysPostEntity>()
                .likeIfPresent(SysPostEntity::getName, dto.getName())
                .eqIfPresent(SysPostEntity::getStatus, dto.getStatus())
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
