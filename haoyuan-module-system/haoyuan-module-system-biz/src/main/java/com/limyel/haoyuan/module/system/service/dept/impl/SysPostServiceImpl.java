package com.limyel.haoyuan.module.system.service.dept.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.convert.dept.PostConvert;
import com.limyel.haoyuan.module.system.dao.dept.SysPostDao;
import com.limyel.haoyuan.module.system.dataobject.dept.PostDO;
import com.limyel.haoyuan.module.system.dto.dept.PostDTO;
import com.limyel.haoyuan.module.system.dto.dept.PostPageDTO;
import com.limyel.haoyuan.module.system.service.dept.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysPostServiceImpl implements PostService {

    @Autowired
    private SysPostDao sysPostDao;

    @Override
    public Long create(PostDTO dto) {
        validateNameUnique(null, dto.getName());
        validateCodeUnique(null, dto.getCode());

        PostDO sysPost = PostConvert.INSTANCE.toEntity(dto);
        sysPostDao.insert(sysPost);

        return sysPost.getId();
    }

    @Override
    public void update(PostDTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateCodeUnique(dto.getId(), dto.getCode());

        PostDO sysPost = PostConvert.INSTANCE.toEntity(dto);
        sysPostDao.updateById(sysPost);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        sysPostDao.deleteById(id);
    }

    @Override
    public PostDO get(Long id) {
        return sysPostDao.selectById(id);
    }

    @Override
    public List<PostDO> getList(PostPageDTO dto) {
        return sysPostDao.selectList(dto);
    }

    @Override
    public PageData<PostDO> getPage(PostPageDTO dto) {
        Page<PostDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<PostDO> wrapperPlus = new LambdaQueryWrapperPlus<PostDO>()
                .likeIfPresent(PostDO::getName, dto.getName())
                .eqIfPresent(PostDO::getStatus, dto.getStatus())
                .orderByAsc(PostDO::getSort);
        sysPostDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        PostDO sysPost = sysPostDao.selectById(id);
        if (sysPost == null) {
            throw new BizException(SysErrorCodeConstant.POST_NOT_FOUND);
        }
    }

    private void validateNameUnique(Long id, String name) {
        PostDO sysPost = sysPostDao.selectByNameAndCode(name, null);
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
        PostDO sysPost = sysPostDao.selectByNameAndCode(null, code);
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
