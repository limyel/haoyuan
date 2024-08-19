package com.limyel.haoyuan.blog.project.controller.app;

import com.limyel.haoyuan.blog.project.service.ProjectService;
import com.limyel.haoyuan.blog.project.vo.project.ProjectListVO;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/get/list")
    public R<List<ProjectListVO>> getList() {
        List<ProjectListVO> result = projectService.getList();
        return R.ok(result);
    }

}
