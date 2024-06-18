package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.dto.post.PostPageDTO;
import com.limyel.haoyuan.blog.main.service.PostService;
import com.limyel.haoyuan.common.web.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController("adminPostController")
//@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public R<?> create(@Validated @RequestBody PostDTO dto) {
        return R.ok();
    }

    @GetMapping("/delete/{id}")
    public R<?> delete(@PathVariable Long id) {
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated @RequestBody PostDTO dto) {
        return R.ok();
    }

    @GetMapping("/get/by/{id}")
    public R<?> getById(@PathVariable Long id) {
        return R.ok();
    }

    @GetMapping("/get/page")
    public R<?> getPage(PostPageDTO dto) {
        return R.ok();
    }

}
