package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.entity.RoleDO;
import com.limyel.haoyuan.module.system.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.module.system.sys.vo.role.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleDO toDO(RoleDTO dto);

    RoleVO toVO(RoleDO role);

}
