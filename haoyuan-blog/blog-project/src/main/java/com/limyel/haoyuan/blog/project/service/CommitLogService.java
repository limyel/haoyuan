package com.limyel.haoyuan.blog.project.service;

import com.limyel.haoyuan.blog.project.dao.CommitLogDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommitLogService {

    private final CommitLogDao commitLogDao;

}
