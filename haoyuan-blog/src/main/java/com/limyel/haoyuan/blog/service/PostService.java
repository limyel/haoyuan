package com.limyel.haoyuan.blog.service;

import com.limyel.haoyuan.blog.convert.PostConvert;
import com.limyel.haoyuan.blog.dao.PostDao;
import com.limyel.haoyuan.blog.dto.post.PostDTO;
import com.limyel.haoyuan.blog.entity.PostEntity;
import com.limyel.haoyuan.blog.exception.BlogErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;

    private final PostTagService postTagService;

    public Long create(PostDTO dto) {
        validateTitleUnique(null, dto.getTitle());
        validateSlugUnique(null, dto.getSlug());

        PostEntity post = PostConvert.INSTANCE.toEntity(dto);
        return post.getId();
    }

    private void validateTitleUnique(Long id, String title) {
        PostEntity post = postDao.selectOne(PostEntity::getTitle, title);
        if (post != null) {
            post.validateUnique(id, BlogErrorCode.POST_SLUG_DUPLICATE);
        }
    }

    private void validateSlugUnique(Long id, String slug) {
        PostEntity post = postDao.selectOne(PostEntity::getSlug, slug);
        if (post != null) {
            post.validateUnique(id, BlogErrorCode.POST_SLUG_DUPLICATE);
        }
    }


}
