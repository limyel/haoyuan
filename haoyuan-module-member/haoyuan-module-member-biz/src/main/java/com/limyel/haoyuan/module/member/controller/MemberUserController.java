package com.limyel.haoyuan.module.member.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.member.service.MemberUserService;
import com.limyel.haoyuan.module.member.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/user")
public class MemberUserController {

    @Autowired
    private MemberUserService memberUserService;

    @GetMapping("/{username}")
    public Result<UserVO> get(@PathVariable String username) {
        UserVO result = memberUserService.get(username);
        return Result.ok(result);
    }

}
