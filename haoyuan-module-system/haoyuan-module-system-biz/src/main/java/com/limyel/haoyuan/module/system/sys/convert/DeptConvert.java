package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.dataobject.DeptDO;
import com.limyel.haoyuan.module.system.sys.dto.dept.DeptDTO;
import com.limyel.haoyuan.module.system.sys.vo.dept.DeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeptConvert {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    DeptDO toEntity(DeptDTO dto);

    DeptVO toVO(DeptDO entity);

    List<DeptVO> toListVO(List<DeptDO> list);

}
