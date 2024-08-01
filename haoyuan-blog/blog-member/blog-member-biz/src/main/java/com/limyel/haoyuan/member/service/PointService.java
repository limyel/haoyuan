package com.limyel.haoyuan.member.service;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.member.dao.PointDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private PointDao pointDao;

    public int create() {
        return 0;
    }

    public int delete(Long userId) {
        return 0;
    }

    public PageData<?> getPage() {
        return null;
    }

    public PageData<?> getList() {
        return null;
    }

}
