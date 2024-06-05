package com.limyel.haoyuan.blog.service;

import com.limyel.haoyuan.blog.dao.PostDao;
import com.limyel.haoyuan.blog.dao.PostTagDao;
import com.limyel.haoyuan.blog.entity.PostTagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostTagService {

    private final PostTagDao postTagDao;

    public void create(Long postId, List<Long> tagIds) {
        postTagDao.deleteByPostId(postId);

        List<PostTagEntity> list = tagIds.stream().map(tagId -> {
            PostTagEntity entity = new PostTagEntity();
            entity.setPostId(postId);
            entity.setTagId(tagId);
            return entity;
        }).collect(Collectors.toList());

        postTagDao.insertBatchSomeColumn(list);
    }

}
