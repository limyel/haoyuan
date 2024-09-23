package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.common.main.vo.dashboard.StatisticsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PostService postService;

    private final TagService tagService;

    public StatisticsVO getStatistics() {
        StatisticsVO result = new StatisticsVO();
        result.setPostNum(postService.getCount());
        result.setTagNum(tagService.getCount());
        result.setTotalViewNum(postService.getAllViewNum());

        return result;
    }

    public Map<String, Long> getPostStatistics() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextYear = now.minusYears(1);

        return null;
    }

}
