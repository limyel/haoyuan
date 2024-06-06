package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.convert.DictDataConvert;
import com.limyel.haoyuan.system.domain.DictDataDO;
import com.limyel.haoyuan.system.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.system.service.DictDataService;
import com.limyel.haoyuan.system.vo.dict.data.DictDataVO;
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
@RequestMapping("/sys/dict-data")
@RequiredArgsConstructor
public class DictDataController {

    private final DictDataService dictDataService;

    @PostMapping
    public R<Long> create(@RequestBody DictDataDTO dto) {
        Long id = dictDataService.create(dto);
        return R.ok(id);
    }

    @PutMapping
    public R<?> update(@RequestBody DictDataDTO dto) {
        dictDataService.update(dto);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        dictDataService.delete(id);
        return new R<>();
    }

    @GetMapping("/{id}")
    public R<DictDataVO> get(@PathVariable("id") Long id) {
        DictDataDO dictData = dictDataService.get(id);
        return R.ok(DictDataConvert.INSTANCE.toVO(dictData));
    }

}