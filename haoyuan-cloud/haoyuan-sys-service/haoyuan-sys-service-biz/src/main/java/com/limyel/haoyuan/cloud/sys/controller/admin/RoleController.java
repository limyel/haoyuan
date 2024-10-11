package com.limyel.haoyuan.cloud.sys.controller.admin;

import com.limyel.haoyuan.cloud.sys.dto.RoleDTO;
import com.limyel.haoyuan.cloud.sys.service.RoleService;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    public R<?> create(@Validated(Create.class) @RequestBody RoleDTO dto) {
        // todo 校验？
        return R.check(roleService.create(dto));
    }

    @GetMapping("/delete/{ids}")
    public R<?> deleteByIds(@PathVariable("ids") Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        return R.check(roleService.deleteByIds(list));
    }

    @PostMapping("/update")
    public R<?> update(@Validated(Update.class) @RequestBody RoleDTO dto) {
        roleService.checkSystemRole();

        roleService.update(dto);
    }



}
