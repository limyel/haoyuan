package com.limyel.blog.dao;

import java.util.List;

import com.limyel.blog.model.entity.TagEntity;

public interface TagDao {

    List<TagEntity> findByIdIn(List<Long> ids);

    TagEntity findBySlug(String slug);

}
