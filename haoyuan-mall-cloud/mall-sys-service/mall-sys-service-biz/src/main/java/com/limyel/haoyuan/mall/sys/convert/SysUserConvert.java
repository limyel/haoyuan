package com.limyel.haoyuan.mall.sys.convert;

import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfoDTO;
import com.limyel.haoyuan.mall.sys.entity.SysUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserInfoDTO toInfoDTO(SysUserEntity entity);

}