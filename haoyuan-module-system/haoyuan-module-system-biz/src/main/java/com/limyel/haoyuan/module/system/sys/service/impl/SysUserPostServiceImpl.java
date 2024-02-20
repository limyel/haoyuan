package com.limyel.haoyuan.module.system.sys.service.impl;

import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dao.UserPostDao;
import com.limyel.haoyuan.module.system.sys.entity.UserPostDO;
import com.limyel.haoyuan.module.system.sys.service.SysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysUserPostServiceImpl implements SysUserPostService {

    @Autowired
    private UserPostDao sysUserPostDao;

    @Override
    public Boolean createUserPosts(Long userId, Set<Long> postIds) {
        return sysUserPostDao.insertBatch(postIds.stream().map(postId -> {
            UserPostDO sysUserPost = new UserPostDO();
            sysUserPost.setPostId(postId);
            sysUserPost.setUserId(userId);
            return sysUserPost;
        }).collect(Collectors.toList()));
    }

    @Override
    public void updateUserPosts(Long userId, Set<Long> newPostIds) {
        LambdaQueryWrapperPlus<UserPostDO> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eq(UserPostDO::getUserId, userId);
        wrapperPlus.select(UserPostDO::getPostId);
        Set<Long> oldPostIds = sysUserPostDao.selectList(wrapperPlus).stream()
                .map(UserPostDO::getPostId)
                .collect(Collectors.toSet());

        Set<Long> deletePostIds = new HashSet<>(oldPostIds);
        deletePostIds.removeAll(newPostIds);
        deleteByPostIds(deletePostIds);

        Set<Long> createPostIds = new HashSet<>(oldPostIds);
        createPostIds.retainAll(newPostIds);
        createUserPosts(userId, createPostIds);
    }

    @Override
    public void deleteByPostIds(Set<Long> postIds) {
        sysUserPostDao.delete(new LambdaQueryWrapperPlus<UserPostDO>()
                .inIfPresent(UserPostDO::getPostId, postIds));
    }

    @Override
    public void deleteByUserId(Long userId) {
        sysUserPostDao.delete(new LambdaQueryWrapperPlus<UserPostDO>()
                .eqIfPresent(UserPostDO::getUserId, userId));
    }

}
