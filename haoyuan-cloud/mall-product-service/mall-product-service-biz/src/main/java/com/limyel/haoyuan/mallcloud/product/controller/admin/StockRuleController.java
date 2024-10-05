package com.limyel.haoyuan.mallcloud.product.controller.admin;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Update;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.common.product.dto.stockrule.StockRuleDTO;
import com.limyel.haoyuan.mall.common.product.dto.stockrule.StockRulePageDTO;
import com.limyel.haoyuan.mall.common.product.vo.stockrule.StockRulePageVO;
import com.limyel.haoyuan.mallcloud.product.service.StockRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("adminStockRuleController")
@RequestMapping("/stock-rule")
@RequiredArgsConstructor
public class StockRuleController {

    private final StockRuleService stockRuleService;

    @PostMapping("/create")
    public R<?> create(@Validated @RequestBody StockRuleDTO dto) {
        stockRuleService.create(dto);
        return R.ok();
    }

    @GetMapping("/delete/by-ids/{ids}")
    public R<?> delete(@PathVariable("ids") String ids) {
        List<Long> idList = new ArrayList<>();
        for (String idStr : ids.split(",")) {
            if (!StringUtils.hasText(idStr) || !idStr.matches("\\d+")) {
                throw new ServiceException("参数异常");
            }
            idList.add(Long.parseLong(idStr));
        }
        stockRuleService.delete(idList);
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated(Update.class) @RequestBody StockRuleDTO dto) {
        stockRuleService.update(dto);
        return R.ok();
    }

    @GetMapping("/get/page")
    public R<PageData<StockRulePageVO>> getPage(StockRulePageDTO dto) {
        PageData<StockRulePageVO> result = stockRuleService.getPage(dto);
        return R.ok(result);
    }

    @GetMapping("/get/by-id/{id}")
    public R<StockRuleDTO> getById(@PathVariable Long id) {
        StockRuleDTO result = stockRuleService.getById(id);
        return R.ok(result);
    }

}
