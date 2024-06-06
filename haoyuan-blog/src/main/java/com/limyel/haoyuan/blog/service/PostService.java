package com.limyel.haoyuan.blog.service;

import com.limyel.haoyuan.blog.convert.PostConvert;
import com.limyel.haoyuan.blog.dao.PostDao;
import com.limyel.haoyuan.blog.dto.post.PostDTO;
import com.limyel.haoyuan.blog.entity.PostDO;
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

        PostDO post = PostConvert.INSTANCE.toEntity(dto);
        return post.getId();
    }

    private void validateTitleUnique(Long id, String title) {
        PostDO post = postDao.selectOne(PostDO::getTitle, title);
        if (post != null) {
            post.validateUnique(id, BlogErrorCode.POST_SLUG_DUPLICATE);
        }
    }

    private void validateSlugUnique(Long id, String slug) {
        PostDO post = postDao.selectOne(PostDO::getSlug, slug);
        if (post != null) {
            post.validateUnique(id, BlogErrorCode.POST_SLUG_DUPLICATE);
        }
    }


}
