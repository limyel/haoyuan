package com.limyel.haoyuan.cloud.todo.service;

import com.limyel.haoyuan.cloud.todo.dao.TaskDao;
import com.limyel.haoyuan.cloud.todo.dao.TaskSnapshootDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskSnapshootService {

    private final TaskSnapshootDao taskSnapshootDao;

}
