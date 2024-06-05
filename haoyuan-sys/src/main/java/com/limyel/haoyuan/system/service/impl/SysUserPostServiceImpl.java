package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.dao.SysUserPostDao;
import com.limyel.haoyuan.system.entity.SysUserPostEntity;
import com.limyel.haoyuan.system.service.SysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysUserPostServiceImpl implements SysUserPostService {

    @Autowired
    private SysUserPostDao sysUserPostDao;

    @Override
    public Integer createUserPosts(Long userId, Set<Long> postIds) {
        return sysUserPostDao.insertBatchSomeColumn(postIds.stream().map(postId -> {
            SysUserPostEntity sysUserPost = new SysUserPostEntity();
            sysUserPost.setPostId(postId);
            sysUserPost.setUserId(userId);
            return sysUserPost;
        }).collect(Collectors.toList()));
    }

    @Override
    public void updateUserPosts(Long userId, Set<Long> newPostIds) {
        LambdaQueryWrapperPlus<SysUserPostEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eq(SysUserPostEntity::getUserId, userId);
        wrapperPlus.select(SysUserPostEntity::getPostId);
        Set<Long> oldPostIds = sysUserPostDao.selectList(wrapperPlus).stream()
                .map(SysUserPostEntity::getPostId)
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
        sysUserPostDao.delete(new LambdaQueryWrapperPlus<SysUserPostEntity>()
                .inIfPresent(SysUserPostEntity::getPostId, postIds));
    }

    @Override
    public void deleteByUserId(Long userId) {
        sysUserPostDao.delete(new LambdaQueryWrapperPlus<SysUserPostEntity>()
                .eqIfPresent(SysUserPostEntity::getUserId, userId));
    }

}