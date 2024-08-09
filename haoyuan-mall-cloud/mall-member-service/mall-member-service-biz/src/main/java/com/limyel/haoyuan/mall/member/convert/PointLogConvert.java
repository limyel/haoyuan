package com.limyel.haoyuan.mall.member.convert;

import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogListVO;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointLogConvert {

    PointLogConvert INSTANCE = Mappers.getMapper(PointLogConvert.class);

    PointLogPageVO toPageVO(PayLogEntity entity);

    PointLogListVO toListVO(PayLogEntity entity);

}