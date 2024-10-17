package com.limyel.haoyuan.cloud.sys.convert;

import com.limyel.haoyuan.cloud.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.cloud.sys.entity.RoleEntity;
import com.limyel.haoyuan.cloud.sys.vo.role.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleEntity toEntity(RoleDTO dto);

    RolePageVO toPageVO(RoleEntity entity);

    RoleDTO toDTO(RoleEntity entity);

}
