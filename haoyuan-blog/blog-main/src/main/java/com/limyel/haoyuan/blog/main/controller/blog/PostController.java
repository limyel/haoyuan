package com.limyel.haoyuan.blog.main.controller.blog;

import com.limyel.haoyuan.blog.main.service.PostService;
import com.limyel.haoyuan.common.web.pojo.PageParam;
import com.limyel.haoyuan.common.web.pojo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("blogPostController")
@RequestMapping("/post")
@RequiredArgsConstructor
@Api(tags = "文章")
public class PostController {

    private final PostService postService;

    @GetMapping("/get/by/{slug}")
    @ApiOperation(value = "文章详情")
    public R<?> getBySlug(@PathVariable String slug) {
        return R.ok();
    }

    @GetMapping("/get/page")
    @ApiOperation(value = "文章分页")
    public R<?> getPage(PageParam pageParam) {
        return R.ok();
    }
}
