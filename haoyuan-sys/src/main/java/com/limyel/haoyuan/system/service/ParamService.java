package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.system.domain.ParamDO;
import com.limyel.haoyuan.system.dto.param.ParamDTO;

public interface ParamService {

    Long create(ParamDTO dto);

    void update(ParamDTO dto);

    void delete(Long id);

    ParamDO get(Long id);

}