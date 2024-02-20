package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.module.system.sys.entity.DictDataDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.data.DictDataDTO;

public interface DictDataService {

    Long create(DictDataDTO dto);

    void update(DictDataDTO dto);

    void delete(Long id);

    DictDataDO get(Long id);

}
