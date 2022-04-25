package com.limyel.haoyuan.api;

import com.limyel.haoyuan.common.api.Response;
import com.limyel.haoyuan.entity.Banner;
import com.limyel.haoyuan.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/by-names")
    public Response<List<Banner>> listByNames(
            @RequestParam List<String> names
    ) {
        List<Banner> banners = bannerService.listByNames(names);
        return Response.success(banners);
    }

}
