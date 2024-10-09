package com.limyel.haoyuan.cloud.mall.trade.convert;

import com.limyel.haoyuan.cloud.mall.trade.vo.userspu.UserProductVO;
import com.limyel.haoyuan.cloud.mall.trade.entity.UserProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSpuConvert {

    UserSpuConvert INSTANCE = Mappers.getMapper(UserSpuConvert.class);

    UserProductVO toVO(UserProductEntity entity);


}