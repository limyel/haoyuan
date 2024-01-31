package com.limyel.haoyuan.module.system.sys.service;

import java.util.Set;

public interface SysUserPostService {

    Boolean createUserPosts(Long userId, Set<Long> postIds);

    void updateUserPosts(Long userId, Set<Long> newPostIds);

    void deleteByPostIds(Set<Long> postIds);

    void deleteByUserId(Long userId);

}
