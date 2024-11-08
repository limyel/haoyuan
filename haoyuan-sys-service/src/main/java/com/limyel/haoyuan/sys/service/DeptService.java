package com.limyel.haoyuan.sys.service;

import com.limyel.haoyuan.cloud.sys.dao.DeptDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeptService {

    private final DeptDao deptDao;

}
