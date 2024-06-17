package com.limyel.haoyuan.blog.main.convert;

import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostDO toEntity(PostDTO dto);

}