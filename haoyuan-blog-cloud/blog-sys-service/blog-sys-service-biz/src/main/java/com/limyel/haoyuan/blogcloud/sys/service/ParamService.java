package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.constant.ParamTypeEnum;
import com.limyel.haoyuan.blogcloud.sys.convert.ParamConvert;
import com.limyel.haoyuan.blogcloud.sys.dao.ParamDao;
import com.limyel.haoyuan.blogcloud.sys.dto.param.ParamDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.ParamEntity;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ParamService {

    private final ParamDao paramDao;

    public Long create(ParamDTO dto) {
        validateCodeUnique(null, dto.getCode());

        ParamEntity param = ParamConvert.INSTANCE.toEntity(dto);
        paramDao.insert(param);
        return param.getId();
    }

    public void update(ParamDTO dto) {
        validateType(dto.getId());
        validateCodeUnique(dto.getId(), dto.getCode());

        ParamEntity param = ParamConvert.INSTANCE.toEntity(dto);
        paramDao.updateById(param);
    }

    public void delete(Long id) {
        validateType(id);

        paramDao.deleteById(id);
    }

    public ParamEntity get(Long id) {
        return paramDao.selectById(id);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        ParamEntity param = paramDao.selectById(id);
        if (param == null) {
            throw new ServiceException();
        }
    }

    private void validateCodeUnique(Long id, String code) {
        ParamEntity param = paramDao.selectByCode(code);
        if (param == null) {
            return;
        }
        if (id == null) {
            throw new ServiceException();
        }
        if (!Objects.equals(id, param.getId())) {
            throw new ServiceException();
        }
    }

    private void validateType(Long id) {
        validateExist(id);
        ParamEntity param = paramDao.selectById(id);
        if (ParamTypeEnum.SYSTEM.getType().equals(param.getType())) {
            throw new ServiceException();
        }
    }

}