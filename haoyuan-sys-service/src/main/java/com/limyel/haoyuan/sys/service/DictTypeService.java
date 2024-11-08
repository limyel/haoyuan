package com.limyel.haoyuan.sys.service;

import com.limyel.haoyuan.cloud.sys.dao.DictTypeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictTypeService {

    private final DictTypeDao dictTypeDao;

}
