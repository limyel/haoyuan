package com.limyel.haoyuan.blog.convert;

import com.limyel.haoyuan.blog.dto.post.PostDTO;
import com.limyel.haoyuan.blog.entity.PostDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostDO toEntity(PostDTO dto);

}