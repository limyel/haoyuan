package com.limyel.haoyuan.mall.member.convert;

import com.limyel.haoyuan.mall.member.entity.PointLogEntity;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogListVO;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointLogConvert {

    PointLogConvert INSTANCE = Mappers.getMapper(PointLogConvert.class);

    PointLogPageVO toPageVO(PointLogEntity entity);

    PointLogListVO toListVO(PointLogEntity entity);

}