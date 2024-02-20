package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.DictTypeConvert;
import com.limyel.haoyuan.module.system.sys.entity.DictTypeDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.module.system.sys.service.DictTypeService;
import com.limyel.haoyuan.module.system.sys.vo.dict.type.DictTypeVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "字典")
@RestController
@RequestMapping("/sys/dict-type")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    @PostMapping
    @PreAuthorize("hasPermission('sys:dict-type:create')")
    public Result<Long> create(@RequestBody DictTypeDTO dto) {
        Long id = dictTypeService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    @PreAuthorize("hasPermission('sys:dict-type:update')")
    public Result<?> update(@RequestBody DictTypeDTO dto) {
        dictTypeService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('sys:dict-type:delete')")
    public Result<?> delete(@PathVariable("id") Long id) {
        dictTypeService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('sys:dict-type:get')")
    public Result<DictTypeVO> get(@PathVariable("id") Long id) {
        DictTypeDO dictType = dictTypeService.get(id);
        return Result.ok(DictTypeConvert.INSTANCE.toVO(dictType));
    }

}
