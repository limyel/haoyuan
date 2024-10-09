package com.limyel.haoyuan.mallcloud.member.convert;

import com.limyel.haoyuan.cloud.member.dto.MemberUserSecurity;
import com.limyel.haoyuan.mallcloud.member.dto.user.UserDTO;
import com.limyel.haoyuan.mallcloud.member.entity.UserEntity;
import com.limyel.haoyuan.mallcloud.member.vo.user.UserInfoVO;
import com.limyel.haoyuan.mallcloud.member.vo.user.UserPageVO;
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