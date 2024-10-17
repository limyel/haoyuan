package com.limyel.haoyuan.cloud.todo.service;

import com.limyel.haoyuan.cloud.todo.dao.TaskDao;
import com.limyel.haoyuan.cloud.todo.dao.TaskStepDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskStepService {

    private final TaskStepDao taskStepDao;

}
