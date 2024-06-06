package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.DictTypeConvert;
import com.limyel.haoyuan.system.dao.DictTypeDao;
import com.limyel.haoyuan.system.domain.DictTypeDO;
import com.limyel.haoyuan.system.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeDao dictTypeDao;

    @Override
    public Long create(DictTypeDTO dto) {
        validateNameUnique(dto.getId(), dto.getName());
        validateTypeUnique(dto.getId(), dto.getType());

        DictTypeDO dictType = DictTypeConvert.INSTANCE.toDO(dto);
        dictTypeDao.insert(dictType);
        return dictType.getId();
    }

    @Override
    public void update(DictTypeDTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateTypeUnique(dto.getId(), dto.getType());

        DictTypeDO dictType = DictTypeConvert.INSTANCE.toDO(dto);
        dictTypeDao.updateById(dictType);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        dictTypeDao.deleteById(id);
    }

    @Override
    public DictTypeDO get(Long id) {
        return dictTypeDao.selectById(id);
    }

    @Override
    public DictTypeDO getByType(String type) {
        return null;
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        DictTypeDO dictType = dictTypeDao.selectById(id);
        if (dictType == null) {
            throw new BizException(SysErrorCode.DICT_TYPE_NOT_FOUND);
        }
    }

    private void validateNameUnique(Long id, String name) {
        DictTypeDO dictType = dictTypeDao.selectByName(name);
        if (dictType == null) {
            return;
        }
        if (id == null) {
            throw new BizException(SysErrorCode.DICT_TYPE_NAME_DUPLICATE);
        }
        if (!Objects.equals(id, dictType.getId())) {
            throw new BizException(SysErrorCode.DICT_TYPE_NAME_DUPLICATE);
        }
    }

    private void validateTypeUnique(Long id, String type) {
        DictTypeDO dictType = dictTypeDao.selectByType(type);
        if (dictType == null) {
            return;
        }
        if (id == null) {
            throw new BizException(SysErrorCode.DICT_TYPE_TYPE_DUPLICATE);
        }
        if (!Objects.equals(id, dictType.getId())) {
            throw new BizException(SysErrorCode.DICT_TYPE_TYPE_DUPLICATE);
        }
    }
}