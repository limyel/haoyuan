package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.main.convert.PostConvert;
import com.limyel.haoyuan.blog.main.dao.PostDao;
import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
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
        PostDO postDO = postDao.selectOne(PostDO::getTitle, title);
        if (postDO != null) {
            postDO.validateUnique(id, MainErrorCode.POST_SLUG_DUPLICATE);
        }
    }

    private void validateSlugUnique(Long id, String slug) {
        PostDO postDO = postDao.selectOne(PostDO::getSlug, slug);
        if (postDO != null) {
            postDO.validateUnique(id, MainErrorCode.POST_SLUG_DUPLICATE);
        }
    }


}
