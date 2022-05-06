package com.limyel.haoyuan.portal.controller;

import com.limyel.haoyuan.common.api.R;
import com.limyel.haoyuan.portal.entity.Banner;
import com.limyel.haoyuan.portal.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * banner 前端控制器
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Tag(name = "Haoyuan BannerController", description = "banner接口")
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Operation(summary = "根据名称获取banner", method = "GET")
    @GetMapping("/by-name")
    public R<List<Banner>> listByNames(
            @RequestParam List<String> nameList
    ) {
        List<Banner> bannerList = bannerService.listByNames(nameList);
        return R.success(bannerList);
    }

}

