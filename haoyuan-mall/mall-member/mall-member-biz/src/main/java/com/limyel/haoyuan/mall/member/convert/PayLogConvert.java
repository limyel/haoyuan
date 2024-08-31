package com.limyel.haoyuan.mall.member.convert;

import com.limyel.haoyuan.mall.member.dto.pointlog.PointChange;
import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.member.vo.pointlog.PayLogListVO;
import com.limyel.haoyuan.mall.member.vo.pointlog.PayLogPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayLogConvert {

    PayLogConvert INSTANCE = Mappers.getMapper(PayLogConvert.class);

    PayLogPageVO toPageVO(PayLogEntity entity);

    PayLogListVO toListVO(PayLogEntity entity);

    PayLogEntity toEntity(PointChange dto);

}