package com.limyel.haoyuan.mall.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.member.convert.PointLogConvert;
import com.limyel.haoyuan.mall.member.dao.PayLogDao;
import com.limyel.haoyuan.mall.member.dto.pointlog.PayLogPageDTO;
import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogListVO;
import com.limyel.haoyuan.mall.member.vo.pointlog.PayLogPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayLogService {

    private final PayLogDao payLogDao;

    private final UserService userService;

    public PageData<PayLogPageVO> getPage(PayLogPageDTO dto) {
        Page<PayLogEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<PayLogEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(PayLogEntity::getUserId, dto.getUserId());
        payLogDao.selectPage(page, wrapperPlus);

        List<PayLogPageVO> list = page.getRecords().stream()
                .map(item -> {
                    PayLogPageVO result = PointLogConvert.INSTANCE.toPageVO(item);
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
        payLogDao.selectPage(page, wrapper);

        List<PointLogListVO> list = page.getRecords().stream()
                .map(PointLogConvert.INSTANCE::toListVO)
                .toList();
        return new PageData<>(page, list);
    }

}
