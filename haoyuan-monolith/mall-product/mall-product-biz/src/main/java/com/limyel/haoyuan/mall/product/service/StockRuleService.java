package com.limyel.haoyuan.mall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.common.product.convert.StockRuleConvert;
import com.limyel.haoyuan.mall.common.product.dto.stockrule.StockRuleDTO;
import com.limyel.haoyuan.mall.common.product.dto.stockrule.StockRulePageDTO;
import com.limyel.haoyuan.mall.common.product.entity.StockRuleEntity;
import com.limyel.haoyuan.mall.common.product.vo.stockrule.StockRulePageVO;
import com.limyel.haoyuan.mall.product.dao.StockRuleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockRuleService {

    private final StockRuleDao stockRuleDao;

    public int create(StockRuleDTO dto) {
        StockRuleEntity stockRule = StockRuleConvert.INSTANCE.toEntity(dto);
        return stockRuleDao.insert(stockRule);
    }

    public int delete(List<Long> ids) {
        return stockRuleDao.deleteBatchIds(ids);
    }

    public int update(StockRuleDTO dto) {
        StockRuleEntity stockRule = StockRuleConvert.INSTANCE.toEntity(dto);
        return stockRuleDao.updateById(stockRule);
    }

    public PageData<StockRulePageVO> getPage(StockRulePageDTO dto) {
        Page<StockRuleEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<StockRuleEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(StockRuleEntity::getSpuId, dto.getSpuId());
        wrapperPlus.eqIfPresent(StockRuleEntity::getType, dto.getType());
        wrapperPlus.eqIfPresent(StockRuleEntity::getType, dto.getType());

        stockRuleDao.selectPage(page, wrapperPlus);

        List<StockRulePageVO> list = page.getRecords().stream()
                .map(StockRuleConvert.INSTANCE::toPageVO)
                .toList();
        return new PageData<>(page, list);
    }

    public StockRuleDTO getById(Long id) {
        StockRuleEntity stockRule = stockRuleDao.selectById(id);
        if (stockRule == null) {
            throw new ServiceException("库存更新规则不存的");
        }
        return StockRuleConvert.INSTANCE.toDTO(stockRule);
    }

}
