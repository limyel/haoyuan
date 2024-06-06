package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.domain.PostDO;
import com.limyel.haoyuan.system.dto.post.PostDTO;
import com.limyel.haoyuan.system.vo.post.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostDO toEntity(PostDTO dto);

    PostVO toVO(PostDO post);

    List<PostVO> toListVO(List<PostDO> list);

}