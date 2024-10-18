package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.common.main.constant.MainErrorMsg;
import com.limyel.haoyuan.blog.common.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.common.main.dto.tag.TagPageDTO;
import com.limyel.haoyuan.blog.common.main.vo.tag.TagPageVO;
import com.limyel.haoyuan.blog.common.main.vo.tag.TagSelectVO;
import com.limyel.haoyuan.blog.main.service.TagService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
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
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/create")
    @ApiOperationLog(description = "添加分类")
    public R<?> create(@Validated @RequestBody TagDTO dto) {
        int result = tagService.create(dto);
        return result == 1 ? R.ok() : R.failed(MainErrorMsg.TAG_CREATE_FAILED);
    }

    @GetMapping("/delete/{slug}")
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
    @ApiOperationLog(description = "分类分页")
    public R<PageData<TagPageVO>> getPage(TagPageDTO dto) {
        PageData<TagPageVO> result = tagService.getPage(dto);
        return R.ok(result);
    }

    @GetMapping("/get/select")
    @ApiOperationLog(description = "分类下拉列表")
    public R<?> getSelect() {
        List<TagSelectVO> result = tagService.getSelect();
        return R.ok(result);
    }

}
