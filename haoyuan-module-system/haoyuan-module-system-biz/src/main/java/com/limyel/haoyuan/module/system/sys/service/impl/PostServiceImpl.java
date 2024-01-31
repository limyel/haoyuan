package com.limyel.haoyuan.module.system.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.exception.SysErrorCode;
import com.limyel.haoyuan.module.system.sys.convert.PostConvert;
import com.limyel.haoyuan.module.system.sys.dao.PostDao;
import com.limyel.haoyuan.module.system.sys.dataobject.PostDO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostDTO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostPageDTO;
import com.limyel.haoyuan.module.system.sys.service.PostService;
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

        PostDO sysPost = PostConvert.INSTANCE.toEntity(dto);
        postDao.insert(sysPost);

        return sysPost.getId();
    }

    @Override
    public void update(PostDTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateCodeUnique(dto.getId(), dto.getCode());

        PostDO post = PostConvert.INSTANCE.toEntity(dto);
        postDao.updateById(post);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        postDao.deleteById(id);
    }

    @Override
    public PostDO get(Long id) {
        return postDao.selectById(id);
    }

    @Override
    public List<PostDO> getList(PostPageDTO dto) {
        return postDao.selectList(dto);
    }

    @Override
    public PageData<PostDO> getPage(PostPageDTO dto) {
        Page<PostDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<PostDO> wrapperPlus = new LambdaQueryWrapperPlus<PostDO>()
                .likeIfPresent(PostDO::getName, dto.getName())
                .eqIfPresent(PostDO::getStatus, dto.getStatus())
                .orderByAsc(PostDO::getSort);
        postDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        PostDO post = postDao.selectById(id);
        if (post == null) {
            throw new BizException(SysErrorCode.POST_NOT_FOUND);
        }
    }

    private void validateNameUnique(Long id, String name) {
        PostDO post = postDao.selectByNameAndCode(name, null);
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
        PostDO post = postDao.selectByNameAndCode(null, code);
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
