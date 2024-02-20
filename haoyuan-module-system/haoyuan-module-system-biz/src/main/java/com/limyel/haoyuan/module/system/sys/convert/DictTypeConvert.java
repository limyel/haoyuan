package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.entity.DictTypeDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.module.system.sys.vo.dict.type.DictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    DictTypeDO toDO(DictTypeDTO dto);

    DictTypeVO toVO(DictTypeDO dictType);

}
