package com.limyel.haoyuan.module.system.convert.user;

import com.limyel.haoyuan.module.system.dataobject.user.SysUserDO;
import com.limyel.haoyuan.module.system.dto.user.SysUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserDO toDO(SysUserDTO dto);

}
