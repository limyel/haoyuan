package com.limyel.haoyuan.mall.member.controller.rpc;

import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

//    @Override
//    public int create(UserCreate dto) {
//        return userService.create(dto);
//    }
//
//    @Override
//    public boolean deductPointBalance(PointBalance dto) {
//        Integer result = userService.deductPointBalance(dto);
//        return result == 1;
//    }

}
