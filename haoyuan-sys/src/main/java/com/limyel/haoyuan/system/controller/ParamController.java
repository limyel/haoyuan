package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.convert.ParamConvert;
import com.limyel.haoyuan.system.domain.ParamDO;
import com.limyel.haoyuan.system.dto.param.ParamDTO;
import com.limyel.haoyuan.system.service.ParamService;
import com.limyel.haoyuan.system.vo.param.ParamVO;
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
@RequestMapping("/sys/param")
@RequiredArgsConstructor
public class ParamController {

    private final ParamService paramService;

    @PostMapping
    public R<Long> create(@RequestBody ParamDTO dto) {
        Long id = paramService.create(dto);
        return R.ok(id);
    }

    @PutMapping
    public R<?> update(@RequestBody ParamDTO dto) {
        paramService.update(dto);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        paramService.delete(id);
        return new R<>();
    }

    @GetMapping("/{id}")
    public R<ParamVO> get(@PathVariable("id") Long id) {
        ParamDO param = paramService.get(id);
        return R.ok(ParamConvert.INSTANCE.toVO(param));
    }

}