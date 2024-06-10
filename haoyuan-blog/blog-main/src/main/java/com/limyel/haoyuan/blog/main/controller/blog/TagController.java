package com.limyel.haoyuan.blog.main.controller.blog;

import com.limyel.haoyuan.blog.main.service.TagService;
import com.limyel.haoyuan.common.web.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("blogTagController")
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/get/{slug}")
    public R<?> getBySlug(@PathVariable String slug) {
        return R.ok();
    }

    @GetMapping("/all")
    public R<?> all() {
        return R.ok();
    }

}
