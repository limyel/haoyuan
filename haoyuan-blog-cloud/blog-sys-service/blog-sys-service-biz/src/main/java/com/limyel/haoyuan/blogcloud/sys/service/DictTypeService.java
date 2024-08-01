package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.convert.DictTypeConvert;
import com.limyel.haoyuan.blogcloud.sys.dao.DictTypeDao;
import com.limyel.haoyuan.blogcloud.sys.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.DictTypeEntity;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DictTypeService {

    private final DictTypeDao dictTypeDao;

    public Long create(DictTypeDTO dto) {
        validateNameUnique(dto.getId(), dto.getName());
        validateTypeUnique(dto.getId(), dto.getType());

        DictTypeEntity dictType = DictTypeConvert.INSTANCE.toEntity(dto);
        dictTypeDao.insert(dictType);
        return dictType.getId();
    }

    public void update(DictTypeDTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateTypeUnique(dto.getId(), dto.getType());

        DictTypeEntity dictType = DictTypeConvert.INSTANCE.toEntity(dto);
        dictTypeDao.updateById(dictType);
    }

    public void delete(Long id) {
        validateExist(id);
        dictTypeDao.deleteById(id);
    }

    public DictTypeEntity get(Long id) {
        return dictTypeDao.selectById(id);
    }

    public DictTypeEntity getByType(String type) {
        return null;
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        DictTypeEntity dictType = dictTypeDao.selectById(id);
        if (dictType == null) {
            throw new ServiceException();
        }
    }

    private void validateNameUnique(Long id, String name) {
        DictTypeEntity dictType = dictTypeDao.selectByName(name);
        if (dictType == null) {
            return;
        }
        if (id == null) {
            throw new ServiceException();
        }
        if (!Objects.equals(id, dictType.getId())) {
            throw new ServiceException();
        }
    }

    private void validateTypeUnique(Long id, String type) {
        DictTypeEntity dictType = dictTypeDao.selectByType(type);
        if (dictType == null) {
            return;
        }
        if (id == null) {
            throw new ServiceException();
        }
        if (!Objects.equals(id, dictType.getId())) {
            throw new ServiceException();
        }
    }
}