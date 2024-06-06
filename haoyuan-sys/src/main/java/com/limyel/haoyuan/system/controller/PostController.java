package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.convert.PostConvert;
import com.limyel.haoyuan.system.domain.PostDO;
import com.limyel.haoyuan.system.dto.post.PostDTO;
import com.limyel.haoyuan.system.dto.post.PostPageDTO;
import com.limyel.haoyuan.system.service.PostService;
import com.limyel.haoyuan.system.vo.post.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/post")
@RequiredArgsConstructor
@Validated
public class PostController {

    private final PostService postService;

    // todo validated
    @PostMapping
    public R<Long> create(@RequestBody PostDTO dto) {
        Long id = postService.create(dto);
        return R.ok(id);
    }

    @PutMapping
    public R<?> update(@RequestBody PostDTO dto) {
        postService.update(dto);
        return new R<>();
    }

    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new R<>();
    }

    @GetMapping("/{id}")
    public R<PostVO> get(@PathVariable("id") Long id) {
        PostDO post = postService.get(id);
        return R.ok(PostConvert.INSTANCE.toVO(post));
    }

    @GetMapping
    public R<PageData<PostVO>> getPage(PostPageDTO dto) {
        PageData<PostDO> page = postService.getPage(dto);
        return R.ok(new PageData<>(page, PostConvert.INSTANCE.toListVO(page.getList())));
    }

}