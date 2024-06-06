package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.domain.SysUserDO;
import com.limyel.haoyuan.system.dto.user.SysUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserDO toDO(SysUserDTO dto);

}