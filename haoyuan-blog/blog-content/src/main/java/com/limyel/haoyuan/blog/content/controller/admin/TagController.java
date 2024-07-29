package com.limyel.haoyuan.blog.content.controller.admin;

import com.limyel.haoyuan.blog.content.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.content.dto.tag.TagPageDTO;
import com.limyel.haoyuan.blog.content.exception.MainErrorCode;
import com.limyel.haoyuan.blog.content.service.TagService;
import com.limyel.haoyuan.blog.content.vo.tag.TagPageVO;
import com.limyel.haoyuan.blog.content.vo.tag.TagSelectVO;
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

    @GetMapping("/delete/{slug}")
    @ApiOperation("删除分类")
    @ApiOperationLog(description = "删除分类")
    public R<?> delete(@PathVariable String slug) {
        tagService.delete(slug);
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated @RequestBody TagDTO dto) {
        return R.ok();
    }

    @GetMapping("/get/by/{id}")
    public R<?> getById(@PathVariable Long id) {
        return R.ok();
    }

    @GetMapping("/get/page")
    @ApiOperation("分类分页")
    @ApiOperationLog(description = "分类分页")
    public R<PageData<TagPageVO>> getPage(TagPageDTO dto) {
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
