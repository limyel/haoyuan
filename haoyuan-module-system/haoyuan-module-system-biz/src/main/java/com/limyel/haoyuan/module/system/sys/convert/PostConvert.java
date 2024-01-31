package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.dto.post.PostDTO;
import com.limyel.haoyuan.module.system.sys.dataobject.PostDO;
import com.limyel.haoyuan.module.system.sys.vo.post.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostDO toEntity(PostDTO dto);

    PostVO toVO(PostDO entity);

    List<PostVO> toListVO(List<PostDO> list);

}
