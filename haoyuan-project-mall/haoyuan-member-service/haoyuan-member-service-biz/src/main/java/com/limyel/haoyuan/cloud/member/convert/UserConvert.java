package com.limyel.haoyuan.cloud.member.convert;

import com.limyel.haoyuan.cloud.member.dto.MemberUserSecurity;
import com.limyel.haoyuan.cloud.member.dto.user.UserDTO;
import com.limyel.haoyuan.cloud.member.entity.UserEntity;
import com.limyel.haoyuan.cloud.member.vo.user.UserPageVO;
import com.limyel.haoyuan.cloud.member.vo.user.UserInfoVO;
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