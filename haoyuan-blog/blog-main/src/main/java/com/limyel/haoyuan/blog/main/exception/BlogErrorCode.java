package com.limyel.haoyuan.blog.main.exception;

import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.InvalidParameterErrorCode;

public interface BlogErrorCode {

    ErrorCode POST_TITLE_DUPLICATE = new InvalidParameterErrorCode("Post.TitleDuplicate", "文章标题重复");

    ErrorCode POST_SLUG_DUPLICATE = new InvalidParameterErrorCode("Post.SlugDuplicate", "文章 slug 重复");

}
