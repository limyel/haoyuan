package com.limyel.haoyuan.blogcloud.sys.controller;

import com.limyel.haoyuan.blogcloud.sys.convert.DictDataConvert;
import com.limyel.haoyuan.blogcloud.sys.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.DictDataEntity;
import com.limyel.haoyuan.blogcloud.sys.service.DictDataService;
import com.limyel.haoyuan.blogcloud.sys.vo.dict.data.DictDataVO;
import com.limyel.haoyuan.common.core.pojo.R;
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
        DictDataEntity dictData = dictDataService.get(id);
        return R.ok(DictDataConvert.INSTANCE.toVO(dictData));
    }

}