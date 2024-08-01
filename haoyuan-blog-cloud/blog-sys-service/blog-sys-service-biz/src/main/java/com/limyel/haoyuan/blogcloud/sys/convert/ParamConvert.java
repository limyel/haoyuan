package com.limyel.haoyuan.blogcloud.sys.convert;

import com.limyel.haoyuan.blogcloud.sys.dto.param.ParamDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.ParamEntity;
import com.limyel.haoyuan.blogcloud.sys.vo.param.ParamVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParamConvert {

    ParamConvert INSTANCE = Mappers.getMapper(ParamConvert.class);

    ParamEntity toEntity(ParamDTO dto);

    ParamVO toVO(ParamEntity param);

}