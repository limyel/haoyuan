package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.DictDataConvert;
import com.limyel.haoyuan.system.dao.DictDataDao;
import com.limyel.haoyuan.system.domain.DictDataDO;
import com.limyel.haoyuan.system.domain.DictTypeDO;
import com.limyel.haoyuan.system.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.system.service.DictDataService;
import com.limyel.haoyuan.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DictDataServiceImpl implements DictDataService {

    @Autowired
    private DictDataDao dictDataDao;

    @Autowired
    private DictTypeService dictTypeService;

    @Override
    public Long create(DictDataDTO dto) {
        validateDictTypeExist(dto.getType());
        validateValueUnique(dto.getId(), dto.getType(), dto.getValue());

        DictDataDO dictData = DictDataConvert.INSTANCE.toDO(dto);
        dictDataDao.insert(dictData);
        return dictData.getId();
    }

    @Override
    public void update(DictDataDTO dto) {
        validateExist(dto.getId());
        validateDictTypeExist(dto.getType());
        validateValueUnique(dto.getId(), dto.getType(), dto.getValue());

        DictDataDO dictData = DictDataConvert.INSTANCE.toDO(dto);
        dictDataDao.updateById(dictData);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        dictDataDao.deleteById(id);
    }

    @Override
    public DictDataDO get(Long id) {
        return dictDataDao.selectById(id);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        DictDataDO dictData = dictDataDao.selectById(id);
        if (dictData == null) {
            throw new BizException(SysErrorCode.DICT_DATA_NOT_FOUND);
        }
    }

    /**
     * 校验字典类型有效性
     * @param type
     */
    private void validateDictTypeExist(String type) {
        DictTypeDO dictType = dictTypeService.getByType(type);
        if (dictType == null) {
            throw new BizException(SysErrorCode.DICT_TYPE_NOT_FOUND);
        }
        if (!StatusEnum.ENABLE.getValue().equals(dictType.getStatus())) {
            throw new BizException(SysErrorCode.DICT_TYPE_NOT_ENABLE);
        }
    }

    /**
     * 校验字典键值唯一性
     * @param id
     * @param type
     * @param value
     */
    private void validateValueUnique(Long id, String type, String value) {
        DictDataDO dictData = dictDataDao.selectByTypeAndValue(type, value);
        if (dictData == null) {
            return;
        }
        if (id == null) {
            throw new BizException(SysErrorCode.DICT_DATA_VALUE_DUPLICATE);
        }
        if (!Objects.equals(id, dictData.getId())) {
            throw new BizException(SysErrorCode.DICT_DATA_VALUE_DUPLICATE);
        }
    }
}