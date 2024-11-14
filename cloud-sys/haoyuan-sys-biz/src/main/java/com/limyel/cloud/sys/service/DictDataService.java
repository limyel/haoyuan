package com.limyel.cloud.sys.service;

import com.limyel.cloud.sys.dao.DictDataDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictDataService {

    private final DictDataDao dictDataDao;

}
