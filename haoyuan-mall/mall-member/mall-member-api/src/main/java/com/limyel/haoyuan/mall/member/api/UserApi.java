package com.limyel.haoyuan.mall.member.api;

import com.limyel.haoyuan.mall.member.dto.user.PointBalance;
import com.limyel.haoyuan.mall.member.dto.user.UserCreate;

public interface UserApi {

    int create(UserCreate dto);

    boolean deductPointBalance(PointBalance dto);

}
