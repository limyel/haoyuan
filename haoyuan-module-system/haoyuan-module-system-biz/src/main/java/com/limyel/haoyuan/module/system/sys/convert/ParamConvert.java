package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.dataobject.ParamDO;
import com.limyel.haoyuan.module.system.sys.dto.param.ParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParamConvert {

    ParamConvert INSTANCE = Mappers.getMapper(ParamConvert.class);

    ParamDO toDO(ParamDTO dto);

}
