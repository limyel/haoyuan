package com.limyel.haoyuan.mall.product.convert;

import com.limyel.haoyuan.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.mall.product.vo.sku.SkuListVO;
import com.limyel.haoyuan.mall.product.vo.sku.SkuPageVO;
import com.limyel.haoyuan.mall.product.dto.sku.SkuDTO;
import com.limyel.haoyuan.mall.product.entity.SkuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkuConvert {

    SkuConvert INSTANCE = Mappers.getMapper(SkuConvert.class);

    SkuEntity toEntity(SkuDTO dto);

    SkuPageVO toPageVO(SkuEntity entity);

    SkuDTO toDTO(SkuEntity entity);

    SkuConfirm toConfirm(SkuEntity entity);

    SkuConfirm toConfirm(SkuDTO dto);

    SkuListVO toListVO(SkuEntity entity);

}