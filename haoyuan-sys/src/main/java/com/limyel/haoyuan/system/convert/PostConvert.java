package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.domain.PostEntity;
import com.limyel.haoyuan.system.dto.post.PostDTO;
import com.limyel.haoyuan.system.vo.post.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostEntity toEntity(PostDTO dto);

    PostVO toVO(PostEntity post);

    List<PostVO> toListVO(List<PostEntity> list);

}