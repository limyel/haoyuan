package com.limyel.haoyuan.module.system.convert.dept;

import com.limyel.haoyuan.module.system.dto.dept.PostDTO;
import com.limyel.haoyuan.module.system.dataobject.dept.PostDO;
import com.limyel.haoyuan.module.system.vo.dept.PostVO;
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
