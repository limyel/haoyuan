package com.limyel.haoyuan.blog.main.controller.blog;

import com.limyel.haoyuan.blog.main.service.TagService;
import com.limyel.haoyuan.blog.main.vo.tag.TagDetailVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagPostVO;
import com.limyel.haoyuan.common.web.log.ApiOperationLog;
import com.limyel.haoyuan.common.web.pojo.R;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("blogTagController")
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/get/all")
    @ApiOperation("所有标签")
    @ApiOperationLog(description = "所有标签")
    public R<List<TagDetailVO>> all() {
        List<TagDetailVO> result = tagService.getAll();
        return R.ok(result);
    }

}
