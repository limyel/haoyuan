package com.limyel.haoyuan.cloud.sys.convert;

import com.limyel.haoyuan.cloud.sys.dto.RoleDTO;
import com.limyel.haoyuan.cloud.sys.dto.SysUserSecurity;
import com.limyel.haoyuan.cloud.sys.entity.RoleEntity;
import com.limyel.haoyuan.cloud.sys.entity.SysUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleEntity toEntity(RoleDTO dto);

}