package com.limyel.haoyuan.module.system.sys.convert;

import com.limyel.haoyuan.module.system.sys.entity.MenuDO;
import com.limyel.haoyuan.module.system.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.module.system.sys.vo.menu.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuDO toDO(MenuDTO dto);

    MenuVO toVO(MenuDO menu);

}
