package com.limyel.haoyuan.blog.convert;

import com.limyel.haoyuan.blog.dto.post.PostDTO;
import com.limyel.haoyuan.blog.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostEntity toEntity(PostDTO dto);

}