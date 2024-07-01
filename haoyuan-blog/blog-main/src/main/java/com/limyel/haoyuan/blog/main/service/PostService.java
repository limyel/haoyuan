package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.main.convert.PostConvert;
import com.limyel.haoyuan.blog.main.dao.PostDao;
import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.dto.post.PostListDTO;
import com.limyel.haoyuan.blog.main.dto.post.PostPageDTO;
import com.limyel.haoyuan.blog.main.event.PostViewEvent;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
import com.limyel.haoyuan.blog.main.vo.post.PostArchiveVO;
import com.limyel.haoyuan.blog.main.vo.post.PostListVO;
import com.limyel.haoyuan.blog.main.vo.post.PostDetailVO;
import com.limyel.haoyuan.blog.main.vo.post.PostPageVO;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;

    private final PostTagService postTagService;

    private final PostContentService postContentService;

    private final ApplicationEventPublisher eventPublisher;

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

    public PageData<PostListVO> getList(PostListDTO dto) {
        Page<PostDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<Long> postIds = postTagService.getPostIdsBySlugs(dto.getTags());

        LambdaQueryWrapperPlus<PostDO> wrapperPlus = new LambdaQueryWrapperPlus<PostDO>();
        wrapperPlus.inIfPresent(PostDO::getId, postIds);
        wrapperPlus.eq(PostDO::getStatus, StatusEnum.ENABLE.getValue());
        wrapperPlus.orderByDesc(PostDO::getTop);
        wrapperPlus.orderByDesc(PostDO::getCreateTime);

        postDao.selectPage(page, wrapperPlus);
        List<PostListVO> list = page.getRecords().stream().map(postDO -> {
            PostListVO vo = PostConvert.INSTANCE.toListVO(postDO);
            vo.setTags(postTagService.getTagsByPostId(postDO.getId()));
            return vo;
        }).toList();
        return new PageData<>(page, list);
    }

    public PostDetailVO getDetail(String slug) {
        LambdaQueryWrapper<PostDO> wrapper = new LambdaQueryWrapper<PostDO>()
                .eq(PostDO::getSlug, slug)
                .eq(PostDO::getStatus, StatusEnum.ENABLE.getValue());
        PostDO postDO = postDao.selectOne(wrapper);
        if (postDO == null) {
            throw new BizException(MainErrorCode.POST_NOT_FOUND);
        }

        PostDetailVO result = PostConvert.INSTANCE.toDetailVO(postDO);
        result.setTags(postTagService.getTagsByPostId(postDO.getId()));
        result.setContent(postContentService.getContent(postDO.getId()));

        eventPublisher.publishEvent(new PostViewEvent(this, postDO.getId()));

        return result;
    }

    public List<PostArchiveVO> getArchive() {
        LambdaQueryWrapper<PostDO> wrapper = new LambdaQueryWrapper<PostDO>()
                .eq(PostDO::getStatus, StatusEnum.ENABLE.getValue())
                .orderByDesc(PostDO::getCreateTime);
        List<PostDO> postDOList = postDao.selectList(wrapper);
        Map<Integer, List<PostDO>> map = postDOList.stream().collect(Collectors.groupingBy(PostDO::getCreateYear));

        List<PostArchiveVO> result = new ArrayList<>();
        map.forEach((year, posts) -> {
            List<PostArchiveVO.Item> items = posts.stream().map(postDO -> {
                PostArchiveVO.Item item = PostConvert.INSTANCE.toArciveVOItem(postDO);
                item.setTags(postTagService.getTagsByPostId(postDO.getId()));
                return item;
            }).toList();
            PostArchiveVO archiveVO = new PostArchiveVO();
            archiveVO.setYear(year);
            archiveVO.setPosts(items);
            result.add(archiveVO);
        });

        Collections.reverse(result);
        return result;
    }

    public Long getCount() {
        return postDao.selectCount(PostDO::getStatus, StatusEnum.ENABLE.getValue());
    }

    public Long getAllViewNum() {
        List<PostDO> list = postDao.selectList(PostDO::getStatus, StatusEnum.ENABLE.getValue());
        return list.stream()
                .mapToLong(PostDO::getViewNum)
                .sum();
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
