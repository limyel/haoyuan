package com.limyel.haoyuan.module.system.sys.service.impl;

import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.module.system.constant.ParamTypeEnum;
import com.limyel.haoyuan.module.system.exception.SysErrorCode;
import com.limyel.haoyuan.module.system.sys.convert.ParamConvert;
import com.limyel.haoyuan.module.system.sys.dao.ParamDao;
import com.limyel.haoyuan.module.system.sys.entity.ParamDO;
import com.limyel.haoyuan.module.system.sys.dto.param.ParamDTO;
import com.limyel.haoyuan.module.system.sys.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamDao paramDao;

    @Override
    public Long create(ParamDTO dto) {
        validateCodeUnique(null, dto.getCode());

        ParamDO param = ParamConvert.INSTANCE.toDO(dto);
        paramDao.insert(param);
        return param.getId();
    }

    @Override
    public void update(ParamDTO dto) {
        validateType(dto.getId());
        validateCodeUnique(dto.getId(), dto.getCode());

        ParamDO param = ParamConvert.INSTANCE.toDO(dto);
        paramDao.updateById(param);
    }

    @Override
    public void delete(Long id) {
        validateType(id);

        paramDao.deleteById(id);
    }

    @Override
    public ParamDO get(Long id) {
        return paramDao.selectById(id);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        ParamDO param = paramDao.selectById(id);
        if (param == null) {
            throw new BizException(SysErrorCode.PARAM_NOT_FOUND);
        }
    }

    private void validateCodeUnique(Long id, String code) {
        ParamDO param = paramDao.selectByCode(code);
        if (param == null) {
            return;
        }
        if (id == null) {
            throw new BizException();
        }
        if (!Objects.equals(id, param.getId())) {
            throw new BizException();
        }
    }

    private void validateType(Long id) {
        validateExist(id);
        ParamDO param = paramDao.selectById(id);
        if (ParamTypeEnum.SYSTEM.getType().equals(param.getType())) {
            throw new BizException();
        }
    }

}
