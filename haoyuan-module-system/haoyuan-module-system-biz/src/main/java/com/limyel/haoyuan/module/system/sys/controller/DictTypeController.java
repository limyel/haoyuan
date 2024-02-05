package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.DictTypeConvert;
import com.limyel.haoyuan.module.system.sys.dataobject.DictTypeDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.module.system.sys.service.DictTypeService;
import com.limyel.haoyuan.module.system.sys.vo.dict.type.DictTypeVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "字典")
@RestController
@RequestMapping("/sys/dict-data")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    @PostMapping
    public Result<Long> create(@RequestBody DictTypeDTO dto) {
        Long id = dictTypeService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody DictTypeDTO dto) {
        dictTypeService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        dictTypeService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<DictTypeVO> get(@PathVariable("id") Long id) {
        DictTypeDO dictType = dictTypeService.get(id);
        return Result.ok(DictTypeConvert.INSTANCE.toVO(dictType));
    }

}
