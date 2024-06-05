package com.limyel.haoyuan.system.service;

import java.util.Set;

public interface SysUserPostService {

    Integer createUserPosts(Long userId, Set<Long> postIds);

    void updateUserPosts(Long userId, Set<Long> newPostIds);

    void deleteByPostIds(Set<Long> postIds);

    void deleteByUserId(Long userId);

}