package com.limyel.haoyuan.blog.admin.convert;

import com.limyel.haoyuan.blog.admin.domain.UserEntity;
import com.limyel.haoyuan.blog.admin.dto.user.UpdatePasswordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserEntity toDO(UpdatePasswordDTO dto);

}