package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blog.main.convert.TagConvert;
import com.limyel.haoyuan.blog.main.dao.PostTagDao;
import com.limyel.haoyuan.blog.main.dao.TagDao;
import com.limyel.haoyuan.blog.main.domain.PostTagDO;
import com.limyel.haoyuan.blog.main.domain.TagDO;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
import com.limyel.haoyuan.blog.main.vo.tag.TagPostVO;
import com.limyel.haoyuan.common.core.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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

        List<PostTagDO> list = tagIds.stream().map(tagId -> {
            PostTagDO entity = new PostTagDO();
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
        List<PostTagDO> postTagDOList = postTagDao.selectList(PostTagDO::getPostId, postId);
        return postTagDOList.stream()
                .map(PostTagDO::getTagId)
                .toList();
    }

    public List<Long> getTagIds(List<String> slugs) {
        List<TagDO> tagDOList = tagDao.selectList(new LambdaQueryWrapper<TagDO>()
                .in(TagDO::getSlug, slugs));
        return tagDOList.stream()
                .map(TagDO::getId)
                .toList();
    }

    public List<Long> getPostIdsByTagIds(List<Long> tagIds) {
        return postTagDao.selectPostIdByTagIds(tagIds);
    }

    public List<Long> getPostIdsBySlugs(List<String> slugs) {
        if (CollectionUtils.isEmpty(slugs)) {
            return List.of();
        }
        List<Long> tagIds = getTagIds(slugs);
        return getPostIdsByTagIds(tagIds);
    }

    public List<TagPostVO> getTagsByPostId(Long postId) {
        List<Long> tagIds = getTagIds(postId);
        if (CollectionUtils.isEmpty(tagIds)) {
            return List.of();
        }
        List<TagDO> tagDOList = tagDao.selectByIds(tagIds);
        return tagDOList.stream()
                .map(TagConvert.INSTANCE::toPostVO)
                .toList();
    }

    private void validateTagIds(List<Long> tagIds) {
        List<TagDO> tagDOList = tagDao.selectByIds(tagIds);
        if (tagDOList.size() != tagIds.size()) {
            throw new BizException(MainErrorCode.TAG_NOT_FOUND);
        }
    }

}
