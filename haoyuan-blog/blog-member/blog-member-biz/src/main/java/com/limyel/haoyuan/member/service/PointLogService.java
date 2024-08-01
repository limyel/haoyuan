package com.limyel.haoyuan.member.service;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.member.dao.PointLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointLogService {

    private final PointLogDao pointLogDao;

    public int create() {
        return 0;
    }

    public int delete(Long userId) {
        return 0;
    }

    public int updatePoint() {
        return 0;
    }

    public PageData<?> getPage() {
        return null;
    }

    public Integer getPoint() {
        return null;
    }

}
