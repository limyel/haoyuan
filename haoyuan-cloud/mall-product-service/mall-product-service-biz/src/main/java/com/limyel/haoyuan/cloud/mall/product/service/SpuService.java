package com.limyel.haoyuan.cloud.mall.product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.cloud.mall.product.convert.SpuConvert;
import com.limyel.haoyuan.cloud.mall.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.cloud.mall.product.dto.spu.SpuListDTO;
import com.limyel.haoyuan.cloud.mall.product.dto.spu.SpuPageDTO;
import com.limyel.haoyuan.cloud.mall.product.entity.SpuEntity;
import com.limyel.haoyuan.cloud.mall.product.vo.spu.SpuListVO;
import com.limyel.haoyuan.cloud.mall.product.vo.spu.SpuPageVO;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.cloud.mall.product.dao.SpuDao;
import com.limyel.haoyuan.cloud.mall.product.dto.SkuConfirm;
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
            throw new ServiceException("SPU 不存在");
        }
        return SpuConvert.INSTANCE.toDTO(spu);
    }

    public List<SkuConfirm> getByIds(List<Long> ids) {
        LambdaQueryWrapper<SpuEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SpuEntity::getId, ids);
        List<SpuEntity> spuList = spuDao.selectList(wrapper);
        return spuList.stream()
                .map(SpuConvert.INSTANCE::toRpcDTO)
                .toList();
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
