package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.system.domain.DictTypeDO;
import com.limyel.haoyuan.system.dto.dict.type.DictTypeDTO;

public interface DictTypeService {

    Long create(DictTypeDTO dto);

    void update(DictTypeDTO dto);

    void delete(Long id);

    DictTypeDO get(Long id);

    DictTypeDO getByType(String type);

}