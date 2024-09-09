package com.limyel.haoyuan.mall.member.convert;

import com.limyel.haoyuan.mall.member.dto.user.UserDTO;
import com.limyel.haoyuan.mall.member.dto.user.MemberUserSecurity;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.vo.user.UserInfoVO;
import com.limyel.haoyuan.mall.member.vo.user.UserPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserEntity toEntity(UserDTO dto);

    UserDTO toDTO(UserEntity entity);

    MemberUserSecurity toSecurity(UserEntity entity);

    UserInfoVO toInfoVO(UserEntity entity);

    UserPageVO toPageVO(UserEntity entity);

}