package com.limyel.haoyuan.cloud.todo.service;

import com.limyel.haoyuan.cloud.todo.dao.GroupDao;
import com.limyel.haoyuan.cloud.todo.dao.TaskDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskDao taskDao;

}
