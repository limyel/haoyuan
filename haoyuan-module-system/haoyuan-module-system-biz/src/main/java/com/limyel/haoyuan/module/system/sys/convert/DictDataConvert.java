package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.dataobject.DictDataDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.data.DictDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataDO toDO(DictDataDTO dto);

}
