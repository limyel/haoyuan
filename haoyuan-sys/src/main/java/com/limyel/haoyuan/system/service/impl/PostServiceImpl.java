package com.limyel.haoyuan.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.PostConvert;
import com.limyel.haoyuan.system.dao.PostDao;
import com.limyel.haoyuan.system.entity.PostEntity;
import com.limyel.haoyuan.system.dto.post.PostDTO;
import com.limyel.haoyuan.system.dto.post.PostPageDTO;
import com.limyel.haoyuan.system.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public Long create(PostDTO dto) {
        validateNameUnique(null, dto.getName());
        validateCodeUnique(null, dto.getCode());

        PostEntity sysPost = PostConvert.INSTANCE.toEntity(dto);
        postDao.insert(sysPost);

        return sysPost.getId();
    }

    @Override
    public void update(PostDTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateCodeUnique(dto.getId(), dto.getCode());

        PostEntity post = PostConvert.INSTANCE.toEntity(dto);
        postDao.updateById(post);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        postDao.deleteById(id);
    }

    @Override
    public PostEntity get(Long id) {
        return postDao.selectById(id);
    }

    @Override
    public List<PostEntity> getList(PostPageDTO dto) {
        return postDao.selectList(dto);
    }

    @Override
    public PageData<PostEntity> getPage(PostPageDTO dto) {
        Page<PostEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<PostEntity> wrapperPlus = new LambdaQueryWrapperPlus<PostEntity>()
                .likeIfPresent(PostEntity::getName, dto.getName())
                .eqIfPresent(PostEntity::getStatus, dto.getStatus())
                .orderByAsc(PostEntity::getSort);
        postDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        PostEntity post = postDao.selectById(id);
        if (post == null) {
            throw new BizException(SysErrorCode.POST_NOT_FOUND);
        }
    }

    private void validateNameUnique(Long id, String name) {
        PostEntity post = postDao.selectByNameAndCode(name, null);
        if (post == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, post.getId())) {
            throw new BizException(SysErrorCode.POST_NAME_DUPLICATE);
        }
    }

    private void validateCodeUnique(Long id, String code) {
        PostEntity post = postDao.selectByNameAndCode(null, code);
        if (post == null) {
            return;
        }
        if (id == null) {
            throw new BizException(SysErrorCode.POST_CODE_DUPLICATE);
        }
        if (!Objects.equals(id, post.getId())) {
            throw new BizException(SysErrorCode.POST_CODE_DUPLICATE);
        }
    }

}