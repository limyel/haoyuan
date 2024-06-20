package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.main.convert.PostConvert;
import com.limyel.haoyuan.blog.main.dao.PostDao;
import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.dto.post.PostPageDTO;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
import com.limyel.haoyuan.blog.main.vo.post.PostPageVO;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.common.web.pojo.PageParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;

    private final PostTagService postTagService;

    private final PostContentService postContentService;

    @Transactional(rollbackFor = Exception.class)
    public int create(PostDTO dto) {
        validateTitleUnique(null, dto.getTitle());
        validateSlugUnique(null, dto.getSlug());

        PostDO postDO = PostConvert.INSTANCE.toEntity(dto);
        int result = postDao.insert(postDO);
        Long postId = postDO.getId();

        postContentService.create(postDO.getId(), dto.getContent());
        postTagService.create(postDO.getId(), dto.getTagIds());

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        validateExist(id);
        postTagService.deleteByPostId(id);
        postContentService.deleteByPostId(id);
        return postDao.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(PostDTO dto) {
        validateExist(dto.getId());
        validateTitleUnique(dto.getId(), dto.getTitle());
        validateSlugUnique(dto.getId(), dto.getSlug());

        PostDO postDO = PostConvert.INSTANCE.toEntity(dto);
        int result = postDao.updateById(postDO);

        postContentService.update(dto.getId(), dto.getContent());
        postTagService.create(dto.getId(), dto.getTagIds());

        return result;
    }

    public PostDTO getById(Long id) {
        PostDO postDO = postDao.selectById(id);
        if (postDO == null) {
            throw new BizException(MainErrorCode.POST_NOT_FOUND);
        }

        PostDTO result = PostConvert.INSTANCE.toDTO(postDO);
        result.setTagIds(postTagService.getTagIds(id));
        result.setContent(postContentService.getContent(id));

        return result;
    }

    public PageData<PostPageVO> getPage(PostPageDTO dto) {
        Page<PostDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<PostDO> wrapperPlus = new LambdaQueryWrapperPlus<PostDO>()
                .likeIfPresent(PostDO::getTitle, dto.getTitle())
                .geIfPresent(PostDO::getCreateTime, dto.getStartTime())
                .leIfPresent(PostDO::getCreateTime, dto.getEndTime());
        postDao.selectPage(page, wrapperPlus);
        return new PageData<>(page, PostConvert.INSTANCE.toPageVO(page.getRecords()));
    }

    public PageData<?> getList(PageParam pageParam) {

    }

    private void validateTitleUnique(Long id, String title) {
        PostDO postDO = postDao.selectOne(PostDO::getTitle, title);
        if (postDO != null) {
            postDO.validateUnique(id, MainErrorCode.POST_SLUG_DUPLICATE);
        }
    }

    private void validateSlugUnique(Long id, String slug) {
        PostDO postDO = postDao.selectOne(PostDO::getSlug, slug);
        if (postDO != null) {
            postDO.validateUnique(id, MainErrorCode.POST_SLUG_DUPLICATE);
        }
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        PostDO postDO = postDao.selectById(id);
        if (postDO == null) {
            throw new BizException(MainErrorCode.POST_NOT_FOUND);
        }
    }

}
