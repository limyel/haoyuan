package com.limyel.haoyuan.module.system.user.convert;

import com.limyel.haoyuan.module.system.dept.convert.SysPostConvert;
import com.limyel.haoyuan.module.system.user.dataobject.SysUserDO;
import com.limyel.haoyuan.module.system.user.dto.SysUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserDO toDO(SysUserDTO dto);

}
