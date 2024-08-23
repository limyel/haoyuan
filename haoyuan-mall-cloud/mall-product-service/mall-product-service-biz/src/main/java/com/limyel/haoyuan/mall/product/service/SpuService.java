package com.limyel.haoyuan.mall.product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.product.constant.SpuRedisKey;
import com.limyel.haoyuan.mall.product.convert.SpuConvert;
import com.limyel.haoyuan.mall.product.dao.SpuDao;
import com.limyel.haoyuan.mall.product.dto.SpuRDTO;
import com.limyel.haoyuan.mall.product.dto.StockDeductRDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuListDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuPageDTO;
import com.limyel.haoyuan.mall.product.entity.SpuEntity;
import com.limyel.haoyuan.mall.product.vo.spu.SpuListVO;
import com.limyel.haoyuan.mall.product.vo.spu.SpuPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpuService {

    private final SpuDao spuDao;

    private final StringRedisTemplate redisTemplate;

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

    public List<SpuRDTO> getByIds(List<Long> ids) {
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

    /**
     * todo 分布式锁
     * 互斥性、可重入性、锁超时，防死锁、锁释放正确，防误删、阻塞和非阻塞、公平和非公平
     * 扣减库存
     * @param dto
     */
    public void deductStock(StockDeductRDTO dto) {
        String orderToken = dto.getOrderToken();
        for (StockDeductRDTO.SpuDTO spuDTO : dto.getSpuList()) {
//            SpuEntity spu = spuDao.selectById(spuDTO.getSpuId());
//            if (spu.getStock() < spuDTO.getQuantity()) {
//                throw new ServiceException("商品库存不足");
//            }
//            spu.setStock(spu.getStock() - spuDTO.getQuantity());
//            spuDao.updateById(spu);

            // 使用 mysql 悲观锁的问题：
            // 1、易造成锁范围过大
            // 2、无法在程序中获取扣减库存之前的库存值
            // 3、很多场景下无法满足业务诉求
            int deductResult = spuDao.update(new LambdaUpdateWrapper<SpuEntity>()
                    .setSql("stock = stock - " + spuDTO.getQuantity())
                    .eq(SpuEntity::getId, spuDTO.getSpuId())
                    .apply("stock >= {0}", spuDTO.getQuantity())
            );
            Assert.isTrue(deductResult == 1, "商品库存不足");

            redisTemplate.opsForList().rightPush(SpuRedisKey.SPU_STOCK_DEDUCT_PREFIX + orderToken, JSONUtil.toJson(spuDTO));
        }
    }

}
