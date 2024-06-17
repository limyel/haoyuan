package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.main.dto.tag.TagPageDTO;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
import com.limyel.haoyuan.blog.main.service.TagService;
import com.limyel.haoyuan.blog.main.vo.tag.TagPageVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagSelectVO;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.web.log.ApiOperationLog;
import com.limyel.haoyuan.common.web.pojo.R;
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

import java.util.List;

@RestController("adminTagController")
@RequestMapping("/tag")
@Api(tags = "Admin 标签模块")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/create")
    @ApiOperation("添加分类")
    @ApiOperationLog(description = "添加分类")
    public R<?> create(@Validated @RequestBody TagDTO dto) {
        int result = tagService.create(dto);
        return result == 1 ? R.ok() : R.failed(MainErrorCode.TAG_CREATE_FAILED);
    }

    @ApiOperation("删除分类")
    @ApiOperationLog(description = "删除分类")
    @GetMapping("/delete/{slug}")
    public R<?> delete(@PathVariable String slug) {
        tagService.delete(slug);
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated @RequestBody TagDTO dto) {
        return R.ok();
    }

    @GetMapping("/get/by/{id}")
    public R<?> get(@PathVariable Long id) {
        return R.ok();
    }

    @GetMapping("/page")
    @ApiOperation("分类分页")
    @ApiOperationLog(description = "分类分页")
    public R<PageData<TagPageVO>> page(TagPageDTO dto) {
        PageData<TagPageVO> result = tagService.getPage(dto);
        return R.ok(result);
    }

    @GetMapping("/get/select")
    @ApiOperation("分类下拉列表")
    @ApiOperationLog(description = "分类下拉列表")
    public R<?> getSelect() {
        List<TagSelectVO> result = tagService.getSelect();
        return R.ok(result);
    }

}
