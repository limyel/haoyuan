package com.limyel.haoyuan.mall.member.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.member.rdto.user.UpdateStatus;
import com.limyel.haoyuan.mall.member.rdto.user.UserDTO;
import com.limyel.haoyuan.mall.member.rdto.user.UserPageDTO;
import com.limyel.haoyuan.mall.member.rdto.user.UserUpdateDTO;
import com.limyel.haoyuan.mall.member.service.UserService;
import com.limyel.haoyuan.mall.member.vo.user.UserPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("adminUserController")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SaCheckLogin(type = "login")
    @PostMapping("/create")
    public R<?> create(@RequestBody UserDTO dto) {
        userService.create(dto);
        return R.ok();
    }

    @SaCheckLogin
    @GetMapping("/delete/{ids}")
    public R<?> delete(@PathVariable("ids") String ids) {
        List<Long> idList = new ArrayList<>();
        for (String idStr : ids.split(",")) {
            if (!StringUtils.hasText(idStr) || !idStr.matches("\\d+")) {
                throw new ServiceException("参数异常");
            }
            idList.add(Long.parseLong(idStr));
        }
        userService.delete(idList);
        return R.ok();
    }

    @PostMapping("/update/status")
    public R<?> updateStatus(@Validated(UpdateStatus.class) @RequestBody UserUpdateDTO dto) {
        userService.updateStatus(dto.getId(), dto.getStatus());
        return R.ok();
    }

    @PostMapping("/update/blog-admin")
    public R<?> updateBlogAdmin(@Validated @RequestBody UserUpdateDTO dto) {
        userService.updateBlogUsername(dto.getId(), dto.getBlogUsername());
        return R.ok();
    }

    @GetMapping("/get/page")
    public R<PageData<UserPageVO>> getPage(UserPageDTO dto) {
        PageData<UserPageVO> result = userService.getPage(dto);
        return R.ok(result);
    }

}
