package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.dataobject.MenuDO;
import com.limyel.haoyuan.module.system.sys.dataobject.RoleDO;
import com.limyel.haoyuan.module.system.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.module.system.sys.dto.role.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleDO toDO(RoleDTO dto);

}
