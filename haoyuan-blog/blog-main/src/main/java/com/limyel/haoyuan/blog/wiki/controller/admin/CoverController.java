package com.limyel.haoyuan.blog.wiki.controller.admin;

import com.limyel.haoyuan.blog.wiki.dto.cover.CoverDTO;
import com.limyel.haoyuan.blog.wiki.dto.cover.CoverPageDTO;
import com.limyel.haoyuan.blog.wiki.service.CoverService;
import com.limyel.haoyuan.blog.wiki.vo.cover.CoverPageVO;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.web.log.ApiOperationLog;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.common.web.validate.Create;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminCoverController")
@RequestMapping("/cover")
@Api(tags = "Admin Wiki 封面模块")
@RequiredArgsConstructor
public class CoverController {

    private final CoverService coverService;

    @PostMapping("/create")
    @ApiOperation("添加封面")
    @ApiOperationLog(description = "添加封面")
    public R<?> create(@Validated(Create.class) @RequestBody CoverDTO dto) {
        coverService.create(dto);
        return R.ok();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation("删除封面")
    @ApiOperationLog(description = "删除封面")
    public R<?> delete(@PathVariable("id") Long id) {
        coverService.delete(id);
        return R.ok();
    }

    @GetMapping("/get/page")
    @ApiOperation("封面分页")
    @ApiOperationLog(description = "封面分页")
    public R<PageData<CoverPageVO>> getPage(CoverPageDTO dto) {
        PageData<CoverPageVO> result = coverService.getPage(dto);
        return R.ok(result);
    }


}
