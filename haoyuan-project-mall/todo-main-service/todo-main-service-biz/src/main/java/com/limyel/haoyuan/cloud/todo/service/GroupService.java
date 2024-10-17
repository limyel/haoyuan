package com.limyel.haoyuan.cloud.todo.service;

import com.limyel.haoyuan.cloud.todo.dao.CategoryDao;
import com.limyel.haoyuan.cloud.todo.dao.GroupDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupDao groupDao;

}
