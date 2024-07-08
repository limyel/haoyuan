package com.limyel.haoyuan.blog.wiki.exception;

import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.InvalidParameterErrorCode;

public interface WikiErrorCode {

    ErrorCode COVER_TITLE_DUPLICATE = new InvalidParameterErrorCode("Cover.TitleDuplicate", "wiki 标题 slug 重复");
    ErrorCode COVER_SLUG_DUPLICATE = new InvalidParameterErrorCode("Cover.SlugDuplicate", "wiki 封面 slug 重复");
    ErrorCode COVER_NOT_FOUND = new InvalidParameterErrorCode("Cover.NotFound", "wiki 封面 slug 重复");
    ErrorCode COVER_HAS_CATALOG = new InvalidParameterErrorCode("Cover.HasCatalog", "封面存在目录未删除");

}
