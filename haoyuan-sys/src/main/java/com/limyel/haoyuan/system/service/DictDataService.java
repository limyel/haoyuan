package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.system.domain.DictDataDO;
import com.limyel.haoyuan.system.dto.dict.data.DictDataDTO;

public interface DictDataService {

    Long create(DictDataDTO dto);

    void update(DictDataDTO dto);

    void delete(Long id);

    DictDataDO get(Long id);

}