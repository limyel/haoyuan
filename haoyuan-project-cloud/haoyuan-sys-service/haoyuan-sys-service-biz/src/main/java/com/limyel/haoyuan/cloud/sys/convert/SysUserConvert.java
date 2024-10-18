package com.limyel.haoyuan.cloud.sys.convert;

import com.limyel.haoyuan.cloud.sys.dto.SysUserSecurity;
import com.limyel.haoyuan.cloud.sys.entity.SysUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserSecurity toSecurity(SysUserEntity entity);

}