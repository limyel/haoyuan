package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.convert.DictTypeConvert;
import com.limyel.haoyuan.system.entity.DictTypeEntity;
import com.limyel.haoyuan.system.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.system.service.DictTypeService;
import com.limyel.haoyuan.system.vo.dict.type.DictTypeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/dict-type")
@RequiredArgsConstructor
public class DictTypeController {

    private final DictTypeService dictTypeService;

    @PostMapping
    public R<Long> create(@RequestBody DictTypeDTO dto) {
        Long id = dictTypeService.create(dto);
        return R.ok(id);
    }

    @PutMapping
    public R<?> update(@RequestBody DictTypeDTO dto) {
        dictTypeService.update(dto);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        dictTypeService.delete(id);
        return new R<>();
    }

    @GetMapping("/{id}")
    public R<DictTypeVO> get(@PathVariable("id") Long id) {
        DictTypeEntity dictType = dictTypeService.get(id);
        return R.ok(DictTypeConvert.INSTANCE.toVO(dictType));
    }

}