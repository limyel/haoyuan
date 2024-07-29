package com.limyel.haoyuan.blog.content.exception;

import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.FailedOperationErrorCode;
import com.limyel.haoyuan.common.core.exception.code.InvalidParameterErrorCode;
import com.limyel.haoyuan.common.core.exception.code.ResourceNotFoundErrorCode;

public interface MainErrorCode {

    ErrorCode POST_TITLE_DUPLICATE = new InvalidParameterErrorCode("Post.TitleDuplicate", "文章标题重复");

    ErrorCode POST_SLUG_DUPLICATE = new InvalidParameterErrorCode("Post.SlugDuplicate", "文章 slug 重复");
    ErrorCode POST_NOT_FOUND = new ResourceNotFoundErrorCode("Post.NotFound", "文章不存在");

    ErrorCode TAG_NAME_DUPLICATE = new InvalidParameterErrorCode("Tag.NameDuplicate", "标签名称重复");
    ErrorCode TAG_SLUG_DUPLICATE = new InvalidParameterErrorCode("Tag.SlugDuplicate", "标签 Slug 重复");
    ErrorCode TAG_CREATE_FAILED = new FailedOperationErrorCode("Tag.Create", "标签添加失败");
    ErrorCode TAG_NOT_FOUND = new ResourceNotFoundErrorCode("Tag.NotFound", "标签不存在");

}
