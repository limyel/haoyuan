package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.module.system.sys.entity.ParamDO;
import com.limyel.haoyuan.module.system.sys.dto.param.ParamDTO;

public interface ParamService {

    Long create(ParamDTO dto);

    void update(ParamDTO dto);

    void delete(Long id);

    ParamDO get(Long id);

}
