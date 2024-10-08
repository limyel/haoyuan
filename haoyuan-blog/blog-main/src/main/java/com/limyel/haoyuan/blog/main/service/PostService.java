package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.common.main.constant.MainErrorMsg;
import com.limyel.haoyuan.blog.common.main.convert.PostConvert;
import com.limyel.haoyuan.blog.common.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.common.main.dto.post.PostListDTO;
import com.limyel.haoyuan.blog.common.main.dto.post.PostPageDTO;
import com.limyel.haoyuan.blog.common.main.dto.post.PostPublishDTO;
import com.limyel.haoyuan.blog.common.main.entity.PostEntity;
import com.limyel.haoyuan.blog.common.main.vo.post.PostArchiveVO;
import com.limyel.haoyuan.blog.common.main.vo.post.PostDetailVO;
import com.limyel.haoyuan.blog.common.main.vo.post.PostListVO;
import com.limyel.haoyuan.blog.common.main.vo.post.PostPageVO;
import com.limyel.haoyuan.blog.main.dao.PostDao;
import com.limyel.haoyuan.blog.main.event.PostViewEvent;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;

    private final PostTagService postTagService;

    private final ApplicationEventPublisher eventPublisher;

    private final RocketMQTemplate rocketMQTemplate;

    @Transactional(rollbackFor = Exception.class)
    public int create(PostDTO dto) {
        postDao.validateUnique(null, PostEntity::getTitle, dto.getTitle(), MainErrorMsg.POST_TITLE_DUPLICATE);
        postDao.validateUnique(null, PostEntity::getSlug, dto.getSlug(), MainErrorMsg.POST_SLUG_DUPLICATE);

        PostEntity post = PostConvert.INSTANCE.toEntity(dto);
        int result = postDao.insert(post);

        postTagService.create(post.getId(), dto.getTagIds());

        PostPublishDTO publishDTO = new PostPublishDTO();
        publishDTO.setId(post.getId());

        // todo
//        PointChange change = new PointChange();
//        change.setChangedPoint(100L);
//        change.setType(StatusEnum.ENABLE.getValue());
//        change.setReason(String.format("发布文章: %s", post.getTitle()));
//        change.setUserId(1L);
//        rocketMQTemplate.asyncSend("point-topic", change, new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                log.info("发布文章: {}, 消息发送成功: {}, ", post.getId(), sendResult.getMsgId());
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                log.error("发布文章: {}, 消息发送失败: {}", post.getId(), throwable.getMessage());
//                throwable.printStackTrace();
//            }
//        });

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        postDao.validateExist(id, MainErrorMsg.POST_NOT_FOUND);

        postTagService.deleteByPostId(id);
        return postDao.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(PostDTO dto) {
        postDao.validateExist(dto.getId(), MainErrorMsg.POST_NOT_FOUND);
        postDao.validateUnique(dto.getId(), PostEntity::getTitle, dto.getTitle(), MainErrorMsg.POST_TITLE_DUPLICATE);
        postDao.validateUnique(dto.getId(), PostEntity::getSlug, dto.getSlug(), MainErrorMsg.POST_SLUG_DUPLICATE);

        PostEntity postDO = PostConvert.INSTANCE.toEntity(dto);
        int result = postDao.updateById(postDO);

        postTagService.create(dto.getId(), dto.getTagIds());

        return result;
    }

    public PostDTO getById(Long id) {
        PostEntity postDO = postDao.selectById(id);
        if (postDO == null) {
            throw new ServiceException("文章不存在");
        }

        PostDTO result = PostConvert.INSTANCE.toDTO(postDO);
        result.setTagIds(postTagService.getTagIds(id));

        return result;
    }

    public PageData<PostPageVO> getPage(PostPageDTO dto) {
        Page<PostEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<PostEntity> wrapperPlus = new LambdaQueryWrapperPlus<PostEntity>()
                .likeIfPresent(PostEntity::getTitle, dto.getTitle())
                .geIfPresent(PostEntity::getCreateTime, dto.getStartTime())
                .leIfPresent(PostEntity::getCreateTime, dto.getEndTime());
        postDao.selectPage(page, wrapperPlus);
        return new PageData<>(page, PostConvert.INSTANCE.toPageVO(page.getRecords()));
    }

    public PageData<PostListVO> getList(PostListDTO dto) {
        Page<PostEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<Long> postIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dto.getTags())) {
            postIds = postTagService.getPostIdsBySlugs(dto.getTags());
            if (postIds.isEmpty()) {
                return new PageData<>();
            }
        }

        LambdaQueryWrapper<PostEntity> wrapper = new LambdaQueryWrapper<PostEntity>();
        wrapper.in(!postIds.isEmpty(), PostEntity::getId, postIds);
        wrapper.eq(PostEntity::getStatus, StatusEnum.ENABLE.getValue());
        wrapper.orderByDesc(PostEntity::getTop);
        wrapper.orderByDesc(PostEntity::getCreateTime);

        postDao.selectPage(page, wrapper);
        List<PostListVO> list = page.getRecords().stream().map(postDO -> {
            PostListVO vo = PostConvert.INSTANCE.toListVO(postDO);
            vo.setTags(postTagService.getTagsByPostId(postDO.getId()));
            return vo;
        }).toList();
        return new PageData<>(page, list);
    }

    public PostDetailVO getDetail(String slug) {
        LambdaQueryWrapper<PostEntity> wrapper = new LambdaQueryWrapper<PostEntity>()
                .eq(PostEntity::getSlug, slug)
                .eq(PostEntity::getStatus, StatusEnum.ENABLE.getValue());
        PostEntity postDO = postDao.selectOne(wrapper);
        if (postDO == null) {
            throw new ServiceException(MainErrorMsg.POST_NOT_FOUND);
        }

        PostDetailVO result = PostConvert.INSTANCE.toDetailVO(postDO);
        result.setTags(postTagService.getTagsByPostId(postDO.getId()));

        eventPublisher.publishEvent(new PostViewEvent(this, postDO.getId()));

        return result;
    }

    public List<PostArchiveVO> getArchive() {
        LambdaQueryWrapper<PostEntity> wrapper = new LambdaQueryWrapper<PostEntity>()
                .eq(PostEntity::getStatus, StatusEnum.ENABLE.getValue())
                .orderByDesc(PostEntity::getCreateTime);
        List<PostEntity> postDOList = postDao.selectList(wrapper);
        Map<Integer, List<PostEntity>> map = postDOList.stream().collect(Collectors.groupingBy(PostEntity::getCreateYear));

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
        return postDao.selectCount(PostEntity::getStatus, StatusEnum.ENABLE.getValue());
    }

    public Long getTodayCount() {
        LambdaQueryWrapper<PostEntity> wrapper = new LambdaQueryWrapper<>();

        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(now.getHour());
        now = now.minusMinutes(now.getMinute());
        wrapper.ge(PostEntity::getCreateTime, now);
        wrapper.eq(PostEntity::getStatus, StatusEnum.ENABLE.getValue());

        return postDao.selectCount(wrapper);
    }

    public Long getAllViewNum() {
        List<PostEntity> list = postDao.selectList(PostEntity::getStatus, StatusEnum.ENABLE.getValue());
        return list.stream()
                .mapToLong(PostEntity::getViewNum)
                .sum();
    }

}
