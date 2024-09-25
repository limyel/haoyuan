package com.limyel.haoyuan.mall.member.api;

import com.limyel.haoyuan.mall.common.auth.dto.MemberUserSecurity;

public interface UserApi {

    MemberUserSecurity getByUsername(String username);

    MemberUserSecurity getByMobile(String mobile);

}
