package com.limyel.haoyuan.blogcloud.sys.convert;

import com.limyel.haoyuan.blogcloud.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.MenuEntity;
import com.limyel.haoyuan.blogcloud.sys.vo.menu.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuEntity toEntity(MenuDTO dto);

    MenuVO toVO(MenuEntity menu);

}