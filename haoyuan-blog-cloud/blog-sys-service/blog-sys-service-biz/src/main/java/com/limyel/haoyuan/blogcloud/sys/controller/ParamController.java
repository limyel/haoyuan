package com.limyel.haoyuan.blogcloud.sys.controller;

import com.limyel.haoyuan.blogcloud.sys.convert.ParamConvert;
import com.limyel.haoyuan.blogcloud.sys.dto.param.ParamDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.ParamEntity;
import com.limyel.haoyuan.blogcloud.sys.service.ParamService;
import com.limyel.haoyuan.blogcloud.sys.vo.param.ParamVO;
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
        ParamEntity param = paramService.get(id);
        return R.ok(ParamConvert.INSTANCE.toVO(param));
    }

}