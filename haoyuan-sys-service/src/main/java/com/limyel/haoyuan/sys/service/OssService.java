package com.limyel.haoyuan.sys.service;

import com.limyel.haoyuan.cloud.sys.dao.OssDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OssService {

    private final OssDao ossDao;

}
