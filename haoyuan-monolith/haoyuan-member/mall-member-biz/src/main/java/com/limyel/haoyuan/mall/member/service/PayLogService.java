package com.limyel.haoyuan.mall.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.common.member.convert.PayLogConvert;
import com.limyel.haoyuan.mall.common.member.dto.paylog.PayLogPageDTO;
import com.limyel.haoyuan.mall.common.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.common.member.vo.paylog.PayLogListVO;
import com.limyel.haoyuan.mall.common.member.vo.paylog.PayLogPageVO;
import com.limyel.haoyuan.mall.member.dao.PayLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayLogService {

    private final PayLogDao payLogDao;

    private final UserService userService;

    public int create(PayLogEntity entity) {
        return payLogDao.insert(entity);
    }

    public PageData<PayLogPageVO> getPage(PayLogPageDTO dto) {
        Page<PayLogEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<PayLogEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(PayLogEntity::getUserId, dto.getUserId());
        payLogDao.selectPage(page, wrapperPlus);

        // todo
//        List<PayLogPageVO> list = page.getRecords().stream()
//                .map(item -> {
//                    PayLogPageVO result = PayLogConvert.INSTANCE.toPageVO(item);
//                    SysUserInfo sysUser = sysUserApi.getById(result.getUserId());
//                    result.setUsername(sysUser.getUsername());
//                    return result;
//                }).toList();
//        return new PageData<>(page, list);
        return null;
    }

    public PageData<PayLogListVO> getList(PageParam pageParam) {

        Page<PayLogEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        LambdaQueryWrapper<PayLogEntity> wrapper = new LambdaQueryWrapper<>();
        // todo
        wrapper.eq(PayLogEntity::getUserId, null);
        payLogDao.selectPage(page, wrapper);

        List<PayLogListVO> list = page.getRecords().stream()
                .map(PayLogConvert.INSTANCE::toListVO)
                .toList();
        return new PageData<>(page, list);
    }

}
