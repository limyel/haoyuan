package com.limyel.haoyuan.blog.main.controller.app;

import com.limyel.haoyuan.blog.common.main.vo.tag.TagDetailVO;
import com.limyel.haoyuan.blog.main.service.TagService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("blogTagController")
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/get/all")
    @ApiOperationLog(description = "所有标签")
    public R<List<TagDetailVO>> all() {
        List<TagDetailVO> result = tagService.getAll();
        return R.ok(result);
    }

}
