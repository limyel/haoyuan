package com.limyel.haoyuan.blogcloud.sys.convert;

import com.limyel.haoyuan.blogcloud.sys.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.DictTypeEntity;
import com.limyel.haoyuan.blogcloud.sys.vo.dict.type.DictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    DictTypeEntity toEntity(DictTypeDTO dto);

    DictTypeVO toVO(DictTypeEntity dictType);

}