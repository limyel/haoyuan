package com.limyel.haoyuan.module.system.convert.dept;

import com.limyel.haoyuan.module.system.dto.dept.SysDeptDTO;
import com.limyel.haoyuan.module.system.dataobject.dept.SysDeptDO;
import com.limyel.haoyuan.module.system.vo.dept.SysDeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDeptConvert {

    SysDeptConvert INSTANCE = Mappers.getMapper(SysDeptConvert.class);

    SysDeptDO toEntity(SysDeptDTO dto);

    SysDeptVO toVO(SysDeptDO entity);

    List<SysDeptVO> toListVO(List<SysDeptDO> list);

}
