package com.limyel.haoyuan.sys.service;

import com.limyel.haoyuan.cloud.sys.dao.DictDataDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictDataService {

    private final DictDataDao dictDataDao;

}
