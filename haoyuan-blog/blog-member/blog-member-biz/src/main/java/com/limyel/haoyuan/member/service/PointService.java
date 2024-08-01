package com.limyel.haoyuan.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.sys.api.SysUserApi;
import com.limyel.haoyuan.blog.sys.vo.user.CurrentUserVO;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.member.constant.PointTypeEnum;
import com.limyel.haoyuan.member.convert.PointConvert;
import com.limyel.haoyuan.member.dao.PointDao;
import com.limyel.haoyuan.member.dto.point.PointDTO;
import com.limyel.haoyuan.member.dto.point.PointPageDTO;
import com.limyel.haoyuan.member.dto.point.PointUpdateDTO;
import com.limyel.haoyuan.member.entity.PointEntity;
import com.limyel.haoyuan.member.vo.point.PointInfoVO;
import com.limyel.haoyuan.member.vo.point.PointPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointDao pointDao;

    private final PointLogService pointLogService;

    private final SysUserApi sysUserApi;

    @Transactional(rollbackFor = Exception.class)
    public int create(PointDTO dto) {
        pointDao.validateUnique(null, PointEntity::getUserId, dto.getUserId(), "用户已创建会员信息");

        PointEntity point = PointConvert.INSTANCE.toEntity(dto);
        return pointDao.insert(point);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long userId) {
        LambdaQueryWrapper<PointEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PointEntity::getUserId, userId);
        PointEntity point = pointDao.selectOne(wrapper);
        if (point == null) {
            throw new ServiceException("会员信息不存在");
        }

        pointLogService.delete(point.getId());
        return pointDao.deleteById(point.getId());
    }

    // todo 并发？
    @Transactional(rollbackFor = Exception.class)
    public int updatePoint(PointUpdateDTO dto) {
        PointEntity point = pointDao.selectOne(PointEntity::getUserId, dto.getUserId());
        if (point == null) {
            throw new ServiceException("会员信息不存在");
        }

        if (PointTypeEnum.ADD.getValue().equals(dto.getType())) {
            point.setPoint(point.getPoint() + dto.getPoint());
        } else if (PointTypeEnum.REDUCE.getValue().equals(dto.getType())) {
            int result = point.getPoint() - dto.getPoint();
            if (result < 0) {
                throw new ServiceException("积分余额不足");
            }
            point.setPoint(result);
        } else {
            throw new ServiceException("操作类型不支持");
        }

        int result = pointDao.updateById(point);

        pointLogService.create(point.getId(), dto);

        return result;
    }

    public PageData<PointPageVO> getPage(PointPageDTO dto) {
        Page<PointEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        Map<Long, String> map = sysUserApi.getIdUsernameMap(dto.getUsername());
        LambdaQueryWrapper<PointEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(PointEntity::getUserId, map.keySet());
        wrapper.orderByDesc(PointEntity::getCreateTime);

        pointDao.selectPage(page, wrapper);

        List<PointPageVO> list = page.getRecords().stream().map(item -> {
            PointPageVO result = PointConvert.INSTANCE.toPageVO(item);
            result.setUsername(map.get(item.getUserId()));
            return result;
        }).toList();
        return new PageData<>(page, list);
    }

    public PointInfoVO getByCurrentUser() {
        CurrentUserVO currentUser = sysUserApi.getCurrentUser();
        PointEntity point = pointDao.selectOne(PointEntity::getUserId, currentUser.getId());
        if (point == null) {
            throw new ServiceException("会员信息不存在");
        }
        return PointConvert.INSTANCE.toInfoVO(point);
    }

    private void validatePoint(PointEntity point, Integer reduce) {
        if (point.getPoint() - reduce < 0) {
            throw new ServiceException("积分余额不足");
        }
    }
}
