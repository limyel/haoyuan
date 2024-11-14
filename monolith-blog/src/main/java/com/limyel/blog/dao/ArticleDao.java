package com.limyel.blog.dao;

import com.limyel.blog.model.entity.ArticleEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleDao {

    List<ArticleEntity> findByCreateTimeBetweenOrderByCreateTimeDesc(LocalDateTime start, LocalDateTime end);

    List<ArticleEntity> findByCreateTimeBetweenAndIdInOrderByCreateTimeDesc(LocalDateTime start, LocalDateTime end, List<Long> ids);

    ArticleEntity findBySlug(String slug);

    @Query("select year(createTime) from ArticleEntity group by year(createTime)")
    List<Integer> findYearByCreateTime();

}
