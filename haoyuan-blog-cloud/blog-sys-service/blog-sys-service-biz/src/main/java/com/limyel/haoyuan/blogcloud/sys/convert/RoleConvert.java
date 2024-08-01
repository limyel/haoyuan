package com.limyel.haoyuan.blogcloud.sys.convert;

import com.limyel.haoyuan.blogcloud.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.RoleEntity;
import com.limyel.haoyuan.blogcloud.sys.vo.role.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleEntity toEntity(RoleDTO dto);

    RoleVO toVO(RoleEntity role);

}