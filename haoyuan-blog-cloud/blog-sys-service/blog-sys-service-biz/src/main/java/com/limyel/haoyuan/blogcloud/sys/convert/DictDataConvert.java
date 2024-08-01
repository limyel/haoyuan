package com.limyel.haoyuan.blogcloud.sys.convert;

import com.limyel.haoyuan.blogcloud.sys.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.DictDataEntity;
import com.limyel.haoyuan.blogcloud.sys.vo.dict.data.DictDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataEntity toEntity(DictDataDTO dto);

    DictDataVO toVO(DictDataEntity dictData);

}