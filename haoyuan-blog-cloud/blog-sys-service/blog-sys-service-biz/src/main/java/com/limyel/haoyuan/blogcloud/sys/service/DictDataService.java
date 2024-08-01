package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.convert.DictDataConvert;
import com.limyel.haoyuan.blogcloud.sys.dao.DictDataDao;
import com.limyel.haoyuan.blogcloud.sys.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.DictDataEntity;
import com.limyel.haoyuan.blogcloud.sys.entity.DictTypeEntity;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DictDataService {

    private final DictDataDao dictDataDao;

    private final DictTypeService dictTypeService;

    public Long create(DictDataDTO dto) {
        validateDictTypeExist(dto.getType());
        validateValueUnique(dto.getId(), dto.getType(), dto.getValue());

        DictDataEntity dictData = DictDataConvert.INSTANCE.toEntity(dto);
        dictDataDao.insert(dictData);
        return dictData.getId();
    }

    public void update(DictDataDTO dto) {
        validateExist(dto.getId());
        validateDictTypeExist(dto.getType());
        validateValueUnique(dto.getId(), dto.getType(), dto.getValue());

        DictDataEntity dictData = DictDataConvert.INSTANCE.toEntity(dto);
        dictDataDao.updateById(dictData);
    }

    public void delete(Long id) {
        validateExist(id);
        dictDataDao.deleteById(id);
    }

    public DictDataEntity get(Long id) {
        return dictDataDao.selectById(id);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        DictDataEntity dictData = dictDataDao.selectById(id);
        if (dictData == null) {
            throw new ServiceException();
        }
    }

    /**
     * 校验字典类型有效性
     * @param type
     */
    private void validateDictTypeExist(String type) {
        DictTypeEntity dictType = dictTypeService.getByType(type);
        if (dictType == null) {
            throw new ServiceException();
        }
        if (!StatusEnum.ENABLE.getValue().equals(dictType.getStatus())) {
            throw new ServiceException();
        }
    }

    /**
     * 校验字典键值唯一性
     * @param id
     * @param type
     * @param value
     */
    private void validateValueUnique(Long id, String type, String value) {
        DictDataEntity dictData = dictDataDao.selectByTypeAndValue(type, value);
        if (dictData == null) {
            return;
        }
        if (id == null) {
            throw new ServiceException();
        }
        if (!Objects.equals(id, dictData.getId())) {
            throw new ServiceException();
        }
    }
}