package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.entity.DeptEntity;
import com.limyel.haoyuan.system.dto.dept.DeptDTO;
import com.limyel.haoyuan.system.vo.dept.DeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeptConvert {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    DeptEntity toEntity(DeptDTO dto);

    DeptVO toVO(DeptEntity entity);

    List<DeptVO> toListVO(List<DeptEntity> list);

}