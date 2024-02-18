package com.limyel.haoyuan.module.system.security.service;

import com.limyel.haoyuan.module.system.security.dataobject.UserTokenDO;
import com.limyel.haoyuan.module.system.security.vo.LoginVO;
import com.limyel.haoyuan.module.system.sys.dataobject.SysUserDO;

public interface UserTokenService {

    UserTokenDO getByUserId(Long userId);

    LoginVO generateToken(Long userId);

    Long getUserIdByToken(String token);

    SysUserDO getUserByToken(String token);

}
