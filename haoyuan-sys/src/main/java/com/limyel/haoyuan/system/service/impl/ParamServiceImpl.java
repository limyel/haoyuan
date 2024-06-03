package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.system.constant.ParamTypeEnum;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.ParamConvert;
import com.limyel.haoyuan.system.dao.ParamDao;
import com.limyel.haoyuan.system.entity.ParamEntity;
import com.limyel.haoyuan.system.dto.param.ParamDTO;
import com.limyel.haoyuan.system.service.ParamService;
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

        ParamEntity param = ParamConvert.INSTANCE.toDO(dto);
        paramDao.insert(param);
        return param.getId();
    }

    @Override
    public void update(ParamDTO dto) {
        validateType(dto.getId());
        validateCodeUnique(dto.getId(), dto.getCode());

        ParamEntity param = ParamConvert.INSTANCE.toDO(dto);
        paramDao.updateById(param);
    }

    @Override
    public void delete(Long id) {
        validateType(id);

        paramDao.deleteById(id);
    }

    @Override
    public ParamEntity get(Long id) {
        return paramDao.selectById(id);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        ParamEntity param = paramDao.selectById(id);
        if (param == null) {
            throw new BizException(SysErrorCode.PARAM_NOT_FOUND);
        }
    }

    private void validateCodeUnique(Long id, String code) {
        ParamEntity param = paramDao.selectByCode(code);
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
        ParamEntity param = paramDao.selectById(id);
        if (ParamTypeEnum.SYSTEM.getType().equals(param.getType())) {
            throw new BizException();
        }
    }

}