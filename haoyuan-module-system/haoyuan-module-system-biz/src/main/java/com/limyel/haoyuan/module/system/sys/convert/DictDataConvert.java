package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.entity.DictDataDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.module.system.sys.vo.dict.data.DictDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataDO toDO(DictDataDTO dto);

    DictDataVO toVO(DictDataDO dictData);

}
