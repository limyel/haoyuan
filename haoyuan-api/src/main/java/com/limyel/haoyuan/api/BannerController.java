package com.limyel.haoyuan.api;

import com.limyel.haoyuan.common.api.Response;
import com.limyel.haoyuan.entity.Banner;
import com.limyel.haoyuan.service.BannerService;
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
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/by-name")
    public Response<List<Banner>> listByNames(
            @RequestParam List<String> nameList
    ) {
        List<Banner> bannerList = bannerService.listByNames(nameList);
        return Response.success(bannerList);
    }

}

