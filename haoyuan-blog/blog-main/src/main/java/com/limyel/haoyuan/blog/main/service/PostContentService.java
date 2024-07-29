package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.limyel.haoyuan.blog.main.dao.PostContentDao;
import com.limyel.haoyuan.blog.main.entity.PostContentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostContentService {

    private final PostContentDao postContentDao;

    public int create(Long postId, String content) {
        PostContentEntity postContentDO = new PostContentEntity();
        postContentDO.setPostId(postId);
        postContentDO.setContent(content);
        return postContentDao.insert(postContentDO);
    }

    public int deleteByPostId(Long postId) {
        return postContentDao.delete(PostContentEntity::getPostId, postId);
    }

    public int update(Long postId, String content) {
        // todo 404?
        LambdaUpdateWrapper<PostContentEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(PostContentEntity::getContent, content);
        updateWrapper.eq(PostContentEntity::getPostId, postId);
        return postContentDao.update(updateWrapper);
    }

    public String getContent(Long postId) {
        PostContentEntity postContentDO = postContentDao.selectOne(PostContentEntity::getPostId, postId);
        return postContentDO == null ? "" : postContentDO.getContent();
    }

}
