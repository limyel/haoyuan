package com.limyel.haoyuan.mall.member.convert;

import com.limyel.haoyuan.mall.member.dto.user.UserInfoDTO;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserInfoDTO toInfoDTO(UserEntity entity);

}