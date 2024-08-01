package com.limyel.haoyuan.blog.sys.api;

import com.limyel.haoyuan.blog.sys.vo.user.CurrentUserVO;

import java.util.Map;

public interface SysUserApi {

    CurrentUserVO getCurrentUser();

    Map<Long, String> getIdUsernameMap(String username);

}
