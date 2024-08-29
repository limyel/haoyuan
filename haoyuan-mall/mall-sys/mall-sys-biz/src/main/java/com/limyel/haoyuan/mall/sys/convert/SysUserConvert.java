package com.limyel.haoyuan.mall.sys.convert;

import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfo;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import com.limyel.haoyuan.mall.sys.entity.SysUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserSecurity toSecurity(SysUserEntity entity);

    SysUserInfo toInfo(SysUserEntity entity);

}