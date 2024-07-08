package com.limyel.haoyuan.blog.wiki.controller.admin;

import com.limyel.haoyuan.blog.wiki.domain.CatalogDO;
import com.limyel.haoyuan.blog.wiki.dto.catalog.CatalogDTO;
import com.limyel.haoyuan.blog.wiki.dto.cover.CoverDTO;
import com.limyel.haoyuan.blog.wiki.service.CatalogService;
import com.limyel.haoyuan.blog.wiki.service.CoverService;
import com.limyel.haoyuan.common.web.log.ApiOperationLog;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.common.web.validate.Create;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminCatalogController")
@RequestMapping("/catalog")
@Api(tags = "Admin Wiki 目录模块")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/create")
    @ApiOperation("添加目录")
    @ApiOperationLog(description = "添加封面")
    public R<?> create(@Validated(Create.class) @RequestBody CatalogDTO dto) {
        catalogService.create(dto);
        return R.ok();
    }



}
