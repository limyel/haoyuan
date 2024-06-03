package com.limyel.haoyuan.system.convert;

import com.limyel.haoyuan.system.entity.MenuEntity;
import com.limyel.haoyuan.system.dto.menu.MenuDTO;
import com.limyel.haoyuan.system.vo.menu.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuEntity toDO(MenuDTO dto);

    MenuVO toVO(MenuEntity menu);

}