package com.limyel.haoyuan.blog.admin.convert;

import com.limyel.haoyuan.blog.admin.domain.UserDO;
import com.limyel.haoyuan.blog.admin.dto.UpdatePasswordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserDO toDO(UpdatePasswordDTO dto);

}