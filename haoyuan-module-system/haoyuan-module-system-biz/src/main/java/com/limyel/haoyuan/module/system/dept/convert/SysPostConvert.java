package com.limyel.haoyuan.module.system.dept.convert;

import com.limyel.haoyuan.module.system.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.system.dept.dataobject.SysPostDO;
import com.limyel.haoyuan.module.system.dept.vo.SysPostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysPostConvert {

    SysPostConvert INSTANCE = Mappers.getMapper(SysPostConvert.class);

    SysPostDO toEntity(SysPostDTO dto);

    SysPostVO toVO(SysPostDO entity);

    List<SysPostVO> toListVO(List<SysPostDO> list);

}
