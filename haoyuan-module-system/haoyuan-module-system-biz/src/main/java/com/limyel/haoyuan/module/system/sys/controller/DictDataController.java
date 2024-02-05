package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.DictDataConvert;
import com.limyel.haoyuan.module.system.sys.convert.PostConvert;
import com.limyel.haoyuan.module.system.sys.dataobject.DictDataDO;
import com.limyel.haoyuan.module.system.sys.dataobject.PostDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.data.DictDataDTO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostDTO;
import com.limyel.haoyuan.module.system.sys.service.DictDataService;
import com.limyel.haoyuan.module.system.sys.vo.dict.data.DictDataVO;
import com.limyel.haoyuan.module.system.sys.vo.post.PostVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "字典")
@RestController
@RequestMapping("/sys/dict-data")
public class DictDataController {

    @Autowired
    private DictDataService dictDataService;

    @PostMapping
    public Result<Long> create(@RequestBody DictDataDTO dto) {
        Long id = dictDataService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody DictDataDTO dto) {
        dictDataService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        dictDataService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<DictDataVO> get(@PathVariable("id") Long id) {
        DictDataDO dictData = dictDataService.get(id);
        return Result.ok(DictDataConvert.INSTANCE.toVO(dictData));
    }

}
