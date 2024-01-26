package com.limyel.haoyuan.module.sys.dept.convert;

import com.limyel.haoyuan.module.sys.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.sys.dept.entity.SysPostEntity;
import com.limyel.haoyuan.module.sys.dept.vo.SysPostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysPostConvert {

    SysPostConvert INSTANCE = Mappers.getMapper(SysPostConvert.class);

    SysPostEntity toEntity(SysPostDTO dto);

    SysPostVO toVO(SysPostEntity entity);

    List<SysPostVO> toListVO(List<SysPostEntity> list);

}
