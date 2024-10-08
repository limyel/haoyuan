package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blog.common.main.constant.MainErrorMsg;
import com.limyel.haoyuan.blog.common.main.convert.TagConvert;
import com.limyel.haoyuan.blog.common.main.entity.PostTagEntity;
import com.limyel.haoyuan.blog.common.main.entity.TagEntity;
import com.limyel.haoyuan.blog.common.main.vo.tag.TagPostVO;
import com.limyel.haoyuan.blog.main.dao.PostTagDao;
import com.limyel.haoyuan.blog.main.dao.TagDao;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

// todo 将 tag 的 slug 和 name 冗余存储？
@Service
@RequiredArgsConstructor
public class PostTagService {

    private final PostTagDao postTagDao;

    private final TagDao tagDao;

    public int create(Long postId, List<Long> tagIds) {
        validateTagIds(tagIds);

        postTagDao.deleteByPostId(postId);

        List<PostTagEntity> list = tagIds.stream().map(tagId -> {
            PostTagEntity entity = new PostTagEntity();
            entity.setPostId(postId);
            entity.setTagId(tagId);
            return entity;
        }).collect(Collectors.toList());

        return postTagDao.insertBatchSomeColumn(list);
    }

    public int deleteByPostId(Long postId) {
        return postTagDao.deleteByPostId(postId);
    }

    public List<Long> getTagIds(Long postId) {
        List<PostTagEntity> postTagDOList = postTagDao.selectList(PostTagEntity::getPostId, postId);
        return postTagDOList.stream()
                .map(PostTagEntity::getTagId)
                .toList();
    }

    public List<Long> getTagIds(List<String> slugs) {
        List<TagEntity> tagDOList = tagDao.selectList(new LambdaQueryWrapper<TagEntity>()
                .in(TagEntity::getSlug, slugs));
        return tagDOList.stream()
                .map(TagEntity::getId)
                .toList();
    }

    public List<TagPostVO> getTagsByPostId(Long postId) {
        List<Long> tagIds = getTagIds(postId);
        if (CollectionUtils.isEmpty(tagIds)) {
            return List.of();
        }
        List<TagEntity> tagDOList = tagDao.selectByIds(tagIds);
        return tagDOList.stream()
                .map(TagConvert.INSTANCE::toPostVO)
                .toList();
    }

    public List<Long> getPostIdsBySlugs(List<String> slugs) {
        if (CollectionUtils.isEmpty(slugs)) {
            return List.of();
        }
        return postTagDao.selectPostIdByTagIds(slugs);
    }

    public long getPostNumByTagId(Long tagId) {
        return postTagDao.selectCount(PostTagEntity::getTagId, tagId);
    }

    private void validateTagIds(List<Long> tagIds) {
        List<TagEntity> tagDOList = tagDao.selectByIds(tagIds);
        if (tagDOList.size() != tagIds.size()) {
            throw new ServiceException(MainErrorMsg.TAG_NOT_FOUND);
        }
    }

}
