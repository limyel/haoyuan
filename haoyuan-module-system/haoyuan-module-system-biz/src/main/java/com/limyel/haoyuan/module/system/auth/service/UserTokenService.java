package com.limyel.haoyuan.module.system.auth.service;

import com.limyel.haoyuan.module.system.auth.vo.LoginVO;
import com.limyel.haoyuan.module.system.sys.dataobject.SysUserDO;

public interface UserTokenService {

    LoginVO generateToken(Long userId);

    Long getUserIdByToken(String token);

    SysUserDO getUserByToken(String token);

}
