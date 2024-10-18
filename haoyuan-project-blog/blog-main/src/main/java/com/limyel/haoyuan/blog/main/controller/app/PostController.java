package com.limyel.haoyuan.blog.main.controller.app;

import com.limyel.haoyuan.blog.main.dto.post.PostListDTO;
import com.limyel.haoyuan.blog.main.service.PostService;
import com.limyel.haoyuan.blog.main.vo.post.PostArchiveVO;
import com.limyel.haoyuan.blog.main.vo.post.PostDetailVO;
import com.limyel.haoyuan.blog.main.vo.post.PostListVO;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("blogPostController")
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/get/detail/{slug}")
    public R<PostDetailVO> getBySlug(@PathVariable String slug) {
        PostDetailVO result = postService.getDetail(slug);
        return R.ok(result);
    }

    @GetMapping("/get/list")
    @ApiOperationLog(description = "文章列表")
    public R<PageData<PostListVO>> getList(PostListDTO dto) {
        PageData<PostListVO> result = postService.getList(dto);
        return R.ok(result);
    }

    @GetMapping("/get/archive")
    @ApiOperationLog(description = "文章归档")
    public R<List<PostArchiveVO>> getArchive() {
        List<PostArchiveVO> result = postService.getArchive();
        return R.ok(result);
    }

    @GetMapping("/get/today-num")
    @ApiOperationLog(description = "当天发布数量")
    public R<Long> getTodayNum() {
        Long result = postService.getTodayCount();
        return R.ok(result);
    }

}
