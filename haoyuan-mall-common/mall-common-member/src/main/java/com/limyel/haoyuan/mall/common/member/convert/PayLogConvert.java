package com.limyel.haoyuan.mall.common.member.convert;

import com.limyel.haoyuan.mall.common.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.common.member.vo.paylog.PayLogListVO;
import com.limyel.haoyuan.mall.common.member.vo.paylog.PayLogPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayLogConvert {

    PayLogConvert INSTANCE = Mappers.getMapper(PayLogConvert.class);

    PayLogPageVO toPageVO(PayLogEntity entity);

    PayLogListVO toListVO(PayLogEntity entity);

}