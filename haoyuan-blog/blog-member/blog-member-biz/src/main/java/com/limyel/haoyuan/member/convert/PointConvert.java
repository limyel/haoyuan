package com.limyel.haoyuan.member.convert;

import com.limyel.haoyuan.member.dto.point.PointDTO;
import com.limyel.haoyuan.member.entity.PointEntity;
import com.limyel.haoyuan.member.vo.point.PointInfoVO;
import com.limyel.haoyuan.member.vo.point.PointPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointConvert {

    PointConvert INSTANCE = Mappers.getMapper(PointConvert.class);

    PointEntity toEntity(PointDTO dto);

    PointInfoVO toInfoVO(PointEntity point);

    PointPageVO toPageVO(PointEntity point);

}