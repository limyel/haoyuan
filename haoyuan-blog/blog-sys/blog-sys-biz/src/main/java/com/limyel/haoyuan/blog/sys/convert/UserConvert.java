package com.limyel.haoyuan.blog.sys.convert;

import com.limyel.haoyuan.blog.sys.domain.UserEntity;
import com.limyel.haoyuan.blog.sys.dto.user.UpdatePasswordDTO;
import com.limyel.haoyuan.blog.sys.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserEntity toEntity(UserDTO dto);

    UserEntity toEntity(UpdatePasswordDTO dto);

}