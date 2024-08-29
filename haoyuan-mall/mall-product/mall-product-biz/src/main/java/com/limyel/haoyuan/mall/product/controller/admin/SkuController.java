package com.limyel.haoyuan.mall.product.controller.admin;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.product.dto.sku.SkuDTO;
import com.limyel.haoyuan.mall.product.dto.sku.SkuPageDTO;
import com.limyel.haoyuan.mall.product.service.SkuService;
import com.limyel.haoyuan.mall.product.vo.sku.SkuPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("adminSkuController")
@RequestMapping("/sku")
@RequiredArgsConstructor
public class SkuController {

    private final SkuService skuService;

    @PostMapping("/create")
    public R<?> create(@Validated(Create.class) @RequestBody SkuDTO dto) {
        skuService.create(dto);
        return R.ok();
    }

    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") String ids) {
        List<Long> idList = new ArrayList<>();
        for (String idStr : ids.split(",")) {
            if (!StringUtils.hasText(idStr) || !idStr.matches("\\d+")) {
                throw new ServiceException("参数异常");
            }
            idList.add(Long.parseLong(idStr));
        }
        skuService.delete(idList);
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated(Update.class) @RequestBody SkuDTO dto) {
        skuService.update(dto);
        return R.ok();
    }

    @GetMapping("/get/page")
    public R<PageData<SkuPageVO>> getPage(SkuPageDTO dto) {
        PageData<SkuPageVO> result = skuService.getPage(dto);
        return R.ok(result);
    }

    @GetMapping("/get/by-id/{id}")
    public R<SkuDTO> getById(@PathVariable("id") Long id) {
        SkuDTO result = skuService.getById(id);
        return R.ok(result);
    }

}
