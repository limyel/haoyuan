package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.DictDataConvert;
import com.limyel.haoyuan.module.system.sys.convert.ParamConvert;
import com.limyel.haoyuan.module.system.sys.dataobject.DictDataDO;
import com.limyel.haoyuan.module.system.sys.dataobject.ParamDO;
import com.limyel.haoyuan.module.system.sys.dto.param.ParamDTO;
import com.limyel.haoyuan.module.system.sys.service.ParamService;
import com.limyel.haoyuan.module.system.sys.vo.dict.data.DictDataVO;
import com.limyel.haoyuan.module.system.sys.vo.param.ParamVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "字典")
@RestController
@RequestMapping("/sys/dict-data")
public class ParamController {

    @Autowired
    private ParamService paramService;

    @PostMapping
    public Result<Long> create(@RequestBody ParamDTO dto) {
        Long id = paramService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody ParamDTO dto) {
        paramService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        paramService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<ParamVO> get(@PathVariable("id") Long id) {
        ParamDO param = paramService.get(id);
        return Result.ok(ParamConvert.INSTANCE.toVO(param));
    }

}
