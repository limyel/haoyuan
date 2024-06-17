package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.main.dao.PostTagDao;
import com.limyel.haoyuan.blog.main.domain.PostTagDO;
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

        List<PostTagDO> list = tagIds.stream().map(tagId -> {
            PostTagDO entity = new PostTagDO();
            entity.setPostId(postId);
            entity.setTagId(tagId);
            return entity;
        }).collect(Collectors.toList());

        postTagDao.insertBatchSomeColumn(list);
    }

}
