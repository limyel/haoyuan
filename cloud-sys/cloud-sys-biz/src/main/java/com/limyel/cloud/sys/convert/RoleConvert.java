package com.limyel.cloud.sys.convert;

import com.limyel.cloud.sys.model.dto.role.RoleDTO;
import com.limyel.cloud.sys.model.entity.RoleEntity;
import com.limyel.cloud.sys.model.vo.role.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleEntity toEntity(RoleDTO dto);

    RolePageVO toPageVO(RoleEntity entity);

    RoleDTO toDTO(RoleEntity entity);

}
