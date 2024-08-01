package com.limyel.haoyuan.blog.main.controller.biz;

import com.limyel.haoyuan.blog.main.dto.post.PostListDTO;
import com.limyel.haoyuan.blog.main.service.PostService;
import com.limyel.haoyuan.blog.main.vo.post.PostArchiveVO;
import com.limyel.haoyuan.blog.main.vo.post.PostDetailVO;
import com.limyel.haoyuan.blog.main.vo.post.PostListVO;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("blogPostController")
@RequestMapping("/post")
@RequiredArgsConstructor
@Api(tags = "文章模块")
public class PostController {

    private final PostService postService;

    @GetMapping("/get/detail/{slug}")
    @ApiOperation(value = "文章详情")
    public R<PostDetailVO> getBySlug(@PathVariable String slug) {
        PostDetailVO result = postService.getDetail(slug);
        return R.ok(result);
    }

    @GetMapping("/get/list")
    @ApiOperation("文章列表")
    @ApiOperationLog(description = "文章列表")
    public R<PageData<PostListVO>> getList(PostListDTO dto) {
        PageData<PostListVO> result = postService.getList(dto);
        return R.ok(result);
    }

    @GetMapping("/get/archive")
    @ApiOperation("文章归档")
    @ApiOperationLog(description = "文章归档")
    public R<List<PostArchiveVO>> getArchive() {
        List<PostArchiveVO> result = postService.getArchive();
        return R.ok(result);
    }
}
