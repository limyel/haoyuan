package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.dto.post.PostPageDTO;
import com.limyel.haoyuan.blog.main.service.PostService;
import com.limyel.haoyuan.blog.main.vo.post.PostPageVO;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminPostController")
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @ApiOperationLog(description = "添加文章")
    public R<?> create(@Validated(Create.class) @RequestBody PostDTO dto) {
        postService.create(dto);
        return R.ok();
    }

    @GetMapping("/delete/{id}")
    @ApiOperationLog(description = "删除文章")
    public R<?> delete(@PathVariable Long id) {
        postService.delete(id);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperationLog(description = "更新文章")
    public R<?> update(@Validated @RequestBody PostDTO dto) {
        postService.update(dto);
        return R.ok();
    }

    @GetMapping("/get/by/{id}")
    @ApiOperationLog(description = "文章详情")
    public R<PostDTO> getById(@PathVariable Long id) {
        PostDTO result = postService.getById(id);
        return R.ok(result);
    }

    @GetMapping("/get/page")
    @ApiOperationLog(description = "文章分页")
    public R<?> getPage(PostPageDTO dto) {
        PageData<PostPageVO> result = postService.getPage(dto);
        return R.ok(result);
    }

}
