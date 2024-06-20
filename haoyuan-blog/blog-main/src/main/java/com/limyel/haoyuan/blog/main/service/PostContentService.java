package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.limyel.haoyuan.blog.main.dao.PostContentDao;
import com.limyel.haoyuan.blog.main.domain.PostContentDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostContentService {

    private final PostContentDao postContentDao;

    public int create(Long postId, String content) {
        PostContentDO postContentDO = new PostContentDO();
        postContentDO.setPostId(postId);
        postContentDO.setContent(content);
        return postContentDao.insert(postContentDO);
    }

    public int deleteByPostId(Long postId) {
        return postContentDao.delete(PostContentDO::getPostId, postId);
    }

    public int update(Long postId, String content) {
        // todo 404?
        LambdaUpdateWrapper<PostContentDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(PostContentDO::getContent, content);
        updateWrapper.eq(PostContentDO::getPostId, postId);
        return postContentDao.update(updateWrapper);
    }

    public String getContent(Long postId) {
        PostContentDO postContentDO = postContentDao.selectOne(PostContentDO::getPostId, postId);
        return postContentDO == null ? "" : postContentDO.getContent();
    }

}
