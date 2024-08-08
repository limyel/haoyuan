package com.limyel.haoyuan.mall.product.convert;

import com.limyel.haoyuan.mall.product.entity.UserSpuEntity;
import com.limyel.haoyuan.mall.product.vo.spu.UserSpuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSpuConvert {

    UserSpuConvert INSTANCE = Mappers.getMapper(UserSpuConvert.class);

    UserSpuVO toVO(UserSpuEntity entity);

}