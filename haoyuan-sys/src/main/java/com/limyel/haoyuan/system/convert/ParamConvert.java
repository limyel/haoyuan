package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.domain.ParamDO;
import com.limyel.haoyuan.system.dto.param.ParamDTO;
import com.limyel.haoyuan.system.vo.param.ParamVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParamConvert {

    ParamConvert INSTANCE = Mappers.getMapper(ParamConvert.class);

    ParamDO toDO(ParamDTO dto);

    ParamVO toVO(ParamDO param);

}