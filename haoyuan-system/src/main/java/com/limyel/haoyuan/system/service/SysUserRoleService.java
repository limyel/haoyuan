package com.limyel.haoyuan.system.service;

import java.util.List;

public interface SysUserRoleService {

    List<Long> listRoleIdByUserId(Long userId);

}