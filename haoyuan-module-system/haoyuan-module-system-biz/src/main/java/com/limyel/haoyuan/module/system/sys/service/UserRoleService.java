package com.limyel.haoyuan.module.system.sys.service;

import java.util.List;

public interface UserRoleService {

    List<Long> listRoleIdByUserId(Long userId);

}
