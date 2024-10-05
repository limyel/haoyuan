package com.limyel.haoyuan.mall.member.controller.rpc;

import com.limyel.haoyuan.mall.common.auth.dto.MemberUserSecurity;
import com.limyel.haoyuan.mall.common.member.convert.UserConvert;
import com.limyel.haoyuan.mall.common.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    @Override
    public MemberUserSecurity getByUsername(String username) {
        UserEntity user = userService.getByUsername(username);
        return UserConvert.INSTANCE.toSecurity(user);
    }

    @Override
    public MemberUserSecurity getByMobile(String mobile) {
        UserEntity user = userService.getByMobild(mobile);
        return UserConvert.INSTANCE.toSecurity(user);
    }

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
