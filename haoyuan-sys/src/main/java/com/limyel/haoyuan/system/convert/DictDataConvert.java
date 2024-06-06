package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.domain.DictDataDO;
import com.limyel.haoyuan.system.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.system.vo.dict.data.DictDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataDO toDO(DictDataDTO dto);

    DictDataVO toVO(DictDataDO dictData);

}