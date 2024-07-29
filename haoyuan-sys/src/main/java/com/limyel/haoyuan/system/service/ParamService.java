package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.system.domain.ParamEntity;
import com.limyel.haoyuan.system.dto.param.ParamDTO;

public interface ParamService {

    Long create(ParamDTO dto);

    void update(ParamDTO dto);

    void delete(Long id);

    ParamEntity get(Long id);

}