package com.limyel.haoyuan.mall.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.member.convert.PointLogConvert;
import com.limyel.haoyuan.mall.member.dao.PointLogDao;
import com.limyel.haoyuan.mall.member.dto.pointlog.PointLogPageDTO;
import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogListVO;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointLogService {

    private final PointLogDao pointLogDao;

    private final UserService userService;

    public PageData<PointLogPageVO> getPage(PointLogPageDTO dto) {
        Page<PayLogEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<PayLogEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(PayLogEntity::getUserId, dto.getUserId());
        pointLogDao.selectPage(page, wrapperPlus);

        List<PointLogPageVO> list = page.getRecords().stream()
                .map(item -> {
                    PointLogPageVO result = PointLogConvert.INSTANCE.toPageVO(item);
                    UserEntity user = userService.getById(result.getUserId());
                    result.setUsername(user.getUsername());
                    return result;
                }).toList();
        return new PageData<>(page, list);
    }

    public PageData<PointLogListVO> getList(PageParam pageParam, Long userId) {
        Page<PayLogEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        LambdaQueryWrapper<PayLogEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PayLogEntity::getUserId, userId);
        pointLogDao.selectPage(page, wrapper);

        List<PointLogListVO> list = page.getRecords().stream()
                .map(PointLogConvert.INSTANCE::toListVO)
                .toList();
        return new PageData<>(page, list);
    }

}
