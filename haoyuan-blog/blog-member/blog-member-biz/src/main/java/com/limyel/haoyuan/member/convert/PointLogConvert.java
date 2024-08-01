package com.limyel.haoyuan.member.convert;

import com.limyel.haoyuan.member.entity.PointLogEntity;
import com.limyel.haoyuan.member.vo.pointlog.PointLogListVO;
import com.limyel.haoyuan.member.vo.pointlog.PointLogPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointLogConvert {

    PointLogConvert INSTANCE = Mappers.getMapper(PointLogConvert.class);

    PointLogListVO toListVO(PointLogEntity pointLog);

    PointLogPageVO toPageVO(PointLogEntity pointLog);

}