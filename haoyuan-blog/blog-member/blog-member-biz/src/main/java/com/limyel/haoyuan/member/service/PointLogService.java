package com.limyel.haoyuan.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.sys.api.SysUserApi;
import com.limyel.haoyuan.blog.sys.vo.user.CurrentUserVO;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.member.convert.PointLogConvert;
import com.limyel.haoyuan.member.dao.PointLogDao;
import com.limyel.haoyuan.member.dto.point.PointUpdateDTO;
import com.limyel.haoyuan.member.dto.pointlog.PointLogPageDTO;
import com.limyel.haoyuan.member.entity.PointLogEntity;
import com.limyel.haoyuan.member.vo.pointlog.PointLogListVO;
import com.limyel.haoyuan.member.vo.pointlog.PointLogPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PointLogService {

    private final PointLogDao pointLogDao;

    private final SysUserApi sysUserApi;

    public int create(Long pointId, PointUpdateDTO dto) {
        PointLogEntity pointLog = new PointLogEntity();
        BeanUtils.copyProperties(dto, pointLog);
        pointLog.setChangedPoint(dto.getPoint());
        pointLog.setPointId(pointId);

        return pointLogDao.insert(pointLog);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long pointId) {
        LambdaQueryWrapper<PointLogEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PointLogEntity::getPointId, pointId);
        return pointLogDao.delete(wrapper);
    }

    public PageData<PointLogPageVO> getPage(PointLogPageDTO dto) {
        Page<PointLogEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        Map<Long, String> map = sysUserApi.getIdUsernameMap(dto.getUsername());
        LambdaQueryWrapper<PointLogEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(PointLogEntity::getUserId, map.keySet());
        wrapper.orderByDesc(PointLogEntity::getCreateTime);

        pointLogDao.selectPage(page, wrapper);

        List<PointLogPageVO> list = page.getRecords().stream().map(item -> {
            PointLogPageVO result = PointLogConvert.INSTANCE.toPageVO(item);
            result.setUsername(map.get(item.getUserId()));
            return result;
        }).toList();

        return new PageData<>(page, list);
    }

    public PageData<PointLogListVO> getList(PageParam pageParam) {
        Page<PointLogEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        CurrentUserVO currentUser = sysUserApi.getCurrentUser();
        Long userId = currentUser.getId();
        LambdaQueryWrapper<PointLogEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PointLogEntity::getUserId, userId);
        wrapper.orderByDesc(PointLogEntity::getCreateTime);

        pointLogDao.selectPage(page, wrapper);

        List<PointLogListVO> list = page.getRecords()
                .stream()
                .map(PointLogConvert.INSTANCE::toListVO).toList();
        return new PageData<>(page, list);
    }

}
