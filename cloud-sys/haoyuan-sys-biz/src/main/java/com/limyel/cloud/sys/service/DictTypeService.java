package com.limyel.cloud.sys.service;

import com.limyel.cloud.sys.dao.DictTypeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DictTypeService {

    private final DictTypeDao dictTypeDao;

}
