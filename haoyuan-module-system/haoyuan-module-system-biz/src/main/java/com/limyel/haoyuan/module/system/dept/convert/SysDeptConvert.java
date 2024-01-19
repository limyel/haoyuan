package com.limyel.haoyuan.module.system.dept.convert;

import com.limyel.haoyuan.module.system.dept.dto.SysDeptDTO;
import com.limyel.haoyuan.module.system.dept.entity.SysDeptEntity;
import com.limyel.haoyuan.module.system.dept.vo.SysDeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDeptConvert {

    SysDeptConvert INSTANCE = Mappers.getMapper(SysDeptConvert.class);

    SysDeptEntity toEntity(SysDeptDTO dto);

    SysDeptVO toVO(SysDeptEntity entity);

    List<SysDeptVO> toListVO(List<SysDeptEntity> list);

}
