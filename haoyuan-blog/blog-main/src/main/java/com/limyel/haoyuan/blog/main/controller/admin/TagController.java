package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.main.dto.tag.TagPageDTO;
import com.limyel.haoyuan.blog.main.service.TagService;
import com.limyel.haoyuan.common.web.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController("adminTagController")
//@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/create")
    public R<?> create(@Validated @RequestBody TagDTO dto) {
        return R.ok();
    }

    @GetMapping("/delete/{id}")
    public R<?> delete(@PathVariable Long id) {
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated @RequestBody TagDTO dto) {
        return R.ok();
    }

    @GetMapping("/get/{id}")
    public R<?> get(@PathVariable Long id) {
        return R.ok();
    }

    @GetMapping("/page")
    public R<?> page(TagPageDTO dto) {
        return R.ok();
    }

}
