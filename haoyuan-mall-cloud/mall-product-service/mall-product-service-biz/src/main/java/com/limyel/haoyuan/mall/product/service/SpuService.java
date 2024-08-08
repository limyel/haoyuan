package com.limyel.haoyuan.mall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.product.convert.SpuConvert;
import com.limyel.haoyuan.mall.product.dao.SpuDao;
import com.limyel.haoyuan.mall.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuListDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuPageDTO;
import com.limyel.haoyuan.mall.product.entity.SpuEntity;
import com.limyel.haoyuan.mall.product.vo.spu.SpuListVO;
import com.limyel.haoyuan.mall.product.vo.spu.SpuPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpuService {

    private final SpuDao spuDao;

    public int create(SpuDTO dto) {
        spuDao.validateUnique(null, SpuEntity::getName, dto.getName(), "商品名称已存在");

        SpuEntity spu = SpuConvert.INSTANCE.toEntity(dto);
        return spuDao.insert(spu);
    }

    public int delete(List<Long> ids) {
        return spuDao.deleteBatchIds(ids);
    }

    public int update(SpuDTO dto) {
        spuDao.validateUnique(dto.getId(), SpuEntity::getName, dto.getName(), "商品名称已存在");

        SpuEntity spu = SpuConvert.INSTANCE.toEntity(dto);
        return spuDao.updateById(spu);
    }

    public PageData<SpuPageVO> getPage(SpuPageDTO dto) {
        Page<SpuEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<SpuEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.likeIfPresent(SpuEntity::getName, dto.getName());
        wrapperPlus.eqIfPresent(SpuEntity::getStatus, dto.getStatus());
        wrapperPlus.eqIfPresent(SpuEntity::getType, dto.getType());
        spuDao.selectPage(page, wrapperPlus);

        List<SpuPageVO> list = page.getRecords().stream()
                .map(SpuConvert.INSTANCE::toPageVO)
                .toList();
        return new PageData<>(page, list);
    }

    public SpuDTO getById(Long id) {
        SpuEntity spu = spuDao.selectById(id);
        if (spu == null) {
            throw new ServiceException("商品不存在");
        }
        return SpuConvert.INSTANCE.toDTO(spu);
    }

    public PageData<SpuListVO> getList(SpuListDTO dto) {
        Page<SpuEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<SpuEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.likeIfPresent(SpuEntity::getName, dto.getName());
        wrapperPlus.eqIfPresent(SpuEntity::getType, dto.getType());

        spuDao.selectPage(page, wrapperPlus);

        List<SpuListVO> list = page.getRecords().stream()
                .map(SpuConvert.INSTANCE::toListVO)
                .toList();
        return new PageData<>(page, list);
    }

}
