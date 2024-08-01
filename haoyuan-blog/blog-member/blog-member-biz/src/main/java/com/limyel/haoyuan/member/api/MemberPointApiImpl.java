package com.limyel.haoyuan.member.api;

import com.limyel.haoyuan.blog.member.api.MemberPointApi;
import com.limyel.haoyuan.blog.member.dto.point.PointChangeDTO;
import com.limyel.haoyuan.blog.member.dto.point.PointCreateDTO;
import com.limyel.haoyuan.member.constant.PointTypeEnum;
import com.limyel.haoyuan.member.dto.point.PointDTO;
import com.limyel.haoyuan.member.dto.point.PointUpdateDTO;
import com.limyel.haoyuan.member.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class MemberPointApiImpl implements MemberPointApi {

    private final PointService pointService;

    @Override
    public int create(@Validated PointCreateDTO dto) {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setUserId(dto.getUserId());
        pointDTO.setPoint(dto.getPoint());
        return pointService.create(pointDTO);
    }

    @Override
    public void addPoint(@Validated PointChangeDTO dto) {
        PointUpdateDTO pointUpdateDTO = getUpdateDTO(dto);
        pointUpdateDTO.setType(PointTypeEnum.ADD.getValue());
        pointService.updatePoint(pointUpdateDTO);
    }

    @Override
    public void reducePoint(@Validated PointChangeDTO dto) {
        PointUpdateDTO pointUpdateDTO = getUpdateDTO(dto);
        pointUpdateDTO.setType(PointTypeEnum.REDUCE.getValue());
        pointService.updatePoint(pointUpdateDTO);
    }

    private PointUpdateDTO getUpdateDTO(PointChangeDTO dto) {
        PointUpdateDTO pointUpdateDTO = new PointUpdateDTO();
        BeanUtils.copyProperties(dto, pointUpdateDTO);
        return pointUpdateDTO;
    }

}
