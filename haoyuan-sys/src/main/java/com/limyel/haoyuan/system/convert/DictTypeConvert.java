package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.domain.DictTypeDO;
import com.limyel.haoyuan.system.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.system.vo.dict.type.DictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    DictTypeDO toDO(DictTypeDTO dto);

    DictTypeVO toVO(DictTypeDO dictType);

}