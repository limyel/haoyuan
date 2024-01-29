package com.limyel.haoyuan.module.system.dept.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.dept.convert.SysPostConvert;
import com.limyel.haoyuan.module.system.dept.dao.SysPostDao;
import com.limyel.haoyuan.module.system.dept.dataobject.SysPostDO;
import com.limyel.haoyuan.module.system.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.system.dept.dto.SysPostFilterDTO;
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

        SysPostDO sysPost = SysPostConvert.INSTANCE.toEntity(dto);
        sysPostDao.insert(sysPost);

        return sysPost.getId();
    }

    @Override
    public void update(SysPostDTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateCodeUnique(dto.getId(), dto.getCode());

        SysPostDO sysPost = SysPostConvert.INSTANCE.toEntity(dto);
        sysPostDao.updateById(sysPost);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        sysPostDao.deleteById(id);
    }

    @Override
    public SysPostDO get(Long id) {
        return sysPostDao.selectById(id);
    }

    @Override
    public List<SysPostDO> getList(SysPostFilterDTO dto) {
        return sysPostDao.selectList(dto);
    }

    @Override
    public PageData<SysPostDO> getPage(SysPostFilterDTO dto) {
        Page<SysPostDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<SysPostDO> wrapperPlus = new LambdaQueryWrapperPlus<SysPostDO>()
                .likeIfPresent(SysPostDO::getName, dto.getName())
                .eqIfPresent(SysPostDO::getStatus, dto.getStatus())
                .orderByAsc(SysPostDO::getSort);
        sysPostDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        SysPostDO sysPost = sysPostDao.selectById(id);
        if (sysPost == null) {
            throw new BizException(SysErrorCodeConstant.POST_NOT_FOUND);
        }
    }

    private void validateNameUnique(Long id, String name) {
        SysPostDO sysPost = sysPostDao.selectByNameAndCode(name, null);
        if (sysPost == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysPost.getId())) {
            throw new BizException(SysErrorCodeConstant.POST_NAME_DUPLICATE);
        }
    }

    private void validateCodeUnique(Long id, String code) {
        SysPostDO sysPost = sysPostDao.selectByNameAndCode(null, code);
        if (sysPost == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysPost.getId())) {
            throw new BizException(SysErrorCodeConstant.POST_CODE_DUPLICATE);
        }
    }

}
