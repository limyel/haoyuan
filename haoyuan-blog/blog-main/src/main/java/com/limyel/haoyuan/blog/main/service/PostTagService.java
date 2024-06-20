package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.main.dao.PostTagDao;
import com.limyel.haoyuan.blog.main.dao.TagDao;
import com.limyel.haoyuan.blog.main.domain.PostTagDO;
import com.limyel.haoyuan.blog.main.domain.TagDO;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
import com.limyel.haoyuan.common.core.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private void validateTagIds(List<Long> tagIds) {
        List<TagDO> tagDOList = tagDao.selectByIds(tagIds);
        if (tagDOList.size() != tagIds.size()) {
            throw new BizException(MainErrorCode.TAG_NOT_FOUND);
        }
    }

}
