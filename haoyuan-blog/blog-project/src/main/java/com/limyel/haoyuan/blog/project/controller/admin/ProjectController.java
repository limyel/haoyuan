package com.limyel.haoyuan.blog.project.controller.admin;

import com.limyel.haoyuan.blog.common.project.dto.project.ProjectDTO;
import com.limyel.haoyuan.blog.common.project.dto.project.ProjectPageDTO;
import com.limyel.haoyuan.blog.common.project.vo.project.ProjectPageVO;
import com.limyel.haoyuan.blog.project.service.ProjectService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminProjectController")
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    @ApiOperation("添加项目")
    @ApiOperationLog(description = "添加项目")
    public R<?> create(@Validated(Create.class) @RequestBody ProjectDTO dto) {
        projectService.create(dto);
        return R.ok();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation("删除项目")
    @ApiOperationLog(description = "删除项目")
    public R<?> delete(@PathVariable Long id) {
        projectService.delete(id);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新文章")
    @ApiOperationLog(description = "更新文章")
    public R<?> update(@Validated(Update.class) @RequestBody ProjectDTO dto) {
        projectService.update(dto);
        return R.ok();
    }

    @GetMapping("/get/by/{id}")
    @ApiOperation("项目详情")
    @ApiOperationLog(description = "项目详情")
    public R<ProjectDTO> getById(@PathVariable Long id) {
        ProjectDTO result = projectService.getById(id);
        return R.ok(result);
    }

    @GetMapping("/get/page")
    @ApiOperation("文章分页")
    @ApiOperationLog(description = "文章分页")
    public R<?> getPage(ProjectPageDTO dto) {
        PageData<ProjectPageVO> result = projectService.getPage(dto);
        return R.ok(result);
    }

}
