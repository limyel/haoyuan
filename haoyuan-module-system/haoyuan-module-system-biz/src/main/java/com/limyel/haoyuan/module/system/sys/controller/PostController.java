package com.limyel.haoyuan.module.system.sys.controller;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.sys.convert.PostConvert;
import com.limyel.haoyuan.module.system.sys.dataobject.PostDO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostDTO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostPageDTO;
import com.limyel.haoyuan.module.system.sys.service.PostService;
import com.limyel.haoyuan.module.system.sys.vo.post.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/sys/post")
@Validated
public class PostController {

    @Autowired
    private PostService postService;

    // todo validated
    @PostMapping
    @PreAuthorize("hasPermission('sys:post:create')")
    public Result<Long> create(@RequestBody PostDTO dto) {
        Long id = postService.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    @PreAuthorize("hasPermission('sys:post:update')")
    public Result<?> update(@RequestBody PostDTO dto) {
        postService.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('sys:post:delete')")
    public Result<?> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('sys:post:get')")
    public Result<PostVO> get(@PathVariable("id") Long id) {
        PostDO post = postService.get(id);
        return Result.ok(PostConvert.INSTANCE.toVO(post));
    }

    @GetMapping
    public Result<PageData<PostVO>> getPage(PostPageDTO dto) {
        PageData<PostDO> page = postService.getPage(dto);
        return Result.ok(new PageData<>(page, PostConvert.INSTANCE.toListVO(page.getList())));
    }

}
