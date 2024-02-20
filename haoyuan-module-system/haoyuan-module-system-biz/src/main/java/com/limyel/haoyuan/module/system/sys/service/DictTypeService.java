package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.module.system.sys.entity.DictTypeDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.type.DictTypeDTO;

public interface DictTypeService {

    Long create(DictTypeDTO dto);

    void update(DictTypeDTO dto);

    void delete(Long id);

    DictTypeDO get(Long id);

    DictTypeDO getByType(String type);

}
