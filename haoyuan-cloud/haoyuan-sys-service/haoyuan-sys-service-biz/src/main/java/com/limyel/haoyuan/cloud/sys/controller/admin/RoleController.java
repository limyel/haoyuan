package com.limyel.haoyuan.cloud.sys.controller.admin;

import com.limyel.haoyuan.cloud.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.cloud.sys.dto.role.RolePageDTO;
import com.limyel.haoyuan.cloud.sys.service.RoleService;
import com.limyel.haoyuan.cloud.sys.vo.role.RolePageVO;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
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
        return R.check(roleService.update(dto));
    }

    @GetMapping("/get-page")
    public R<PageData<RolePageVO>> getPage(@Validated RolePageDTO dto) {
        PageData<RolePageVO> result = roleService.getPage(dto);
        return R.ok(result);
    }

    @GetMapping("/get/{id}")
    public R<?> getById(@PathVariable("id") Long id) {
        RoleDTO result = roleService.getById(id);
        return R.ok(result);
    }

}
