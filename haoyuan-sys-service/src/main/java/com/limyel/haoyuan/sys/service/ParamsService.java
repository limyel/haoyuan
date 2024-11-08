package com.limyel.haoyuan.sys.service;

import com.limyel.haoyuan.cloud.sys.dao.ParamsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParamsService {

    private final ParamsDao paramsDao;

}
