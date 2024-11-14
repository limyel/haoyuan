package com.limyel.blog.dao;

import com.limyel.blog.model.entity.ArticleTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleTagDao {

    List<ArticleTagEntity> findByArticleId(Long articleId);

    List<ArticleTagEntity> findByTagId(Long tagId);

    int deleteByArticleId(Long articleId);

}
