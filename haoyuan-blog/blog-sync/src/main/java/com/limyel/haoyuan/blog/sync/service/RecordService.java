package com.limyel.haoyuan.blog.sync.service;

import com.limyel.haoyuan.blog.sync.dao.RecordDao;
import com.limyel.haoyuan.blog.sync.entity.RecordEntity;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordDao recordDao;

    public int create(Long point, String reason, String username) {
        RecordEntity record = new RecordEntity();
        record.setPoint(point);
        record.setReason(reason);
        record.setUsername(username);
        record.setStatus(StatusEnum.DISABLE.getValue());

        return recordDao.insert(record);
    }

    /**
     * 每天凌晨一点同步
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void sync() {
        List<RecordEntity> records = recordDao.selectList(RecordEntity::getStatus, StatusEnum.DISABLE.getValue());
        for (RecordEntity record : records) {

        }
    }

}
