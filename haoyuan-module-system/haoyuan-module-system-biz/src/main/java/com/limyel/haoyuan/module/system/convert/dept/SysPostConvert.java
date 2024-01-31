package com.limyel.haoyuan.module.system.convert.dept;

import com.limyel.haoyuan.module.system.dto.dept.SysPostDTO;
import com.limyel.haoyuan.module.system.dataobject.dept.SysPostDO;
import com.limyel.haoyuan.module.system.vo.dept.SysPostVO;
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
