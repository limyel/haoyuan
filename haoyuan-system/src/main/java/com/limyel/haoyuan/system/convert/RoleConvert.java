package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.entity.RoleEntity;
import com.limyel.haoyuan.system.dto.role.RoleDTO;
import com.limyel.haoyuan.system.vo.role.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleEntity toDO(RoleDTO dto);

    RoleVO toVO(RoleEntity role);

}