package com.limyel.haoyuan.blog.project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.project.convert.ProjectConvert;
import com.limyel.haoyuan.blog.project.dao.ProjectDao;
import com.limyel.haoyuan.blog.project.dto.project.ProjectDTO;
import com.limyel.haoyuan.blog.project.dto.project.ProjectPageDTO;
import com.limyel.haoyuan.blog.project.entity.ProjectEntity;
import com.limyel.haoyuan.blog.project.vo.project.ProjectListVO;
import com.limyel.haoyuan.blog.project.vo.project.ProjectPageVO;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDao projectDao;

    @Transactional(rollbackFor = Exception.class)
    public int create(ProjectDTO dto) {
        projectDao.validateUnique(null, ProjectEntity::getName, dto.getName(), null);

        ProjectEntity project = ProjectConvert.INSTANCE.toEntity(dto);

        return projectDao.insert(project);
    }

    public int delete(Long id) {
        projectDao.validateExist(id, null);
        return projectDao.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(ProjectDTO dto) {
        projectDao.validateExist(dto.getId(), null);
        projectDao.validateUnique(dto.getId(), ProjectEntity::getName, dto.getName(), null);

        ProjectEntity project = ProjectConvert.INSTANCE.toEntity(dto);
        return projectDao.updateById(project);
    }

    public ProjectDTO getById(Long id) {
        ProjectEntity project = projectDao.selectById(id);
        if (project == null) {
            throw new ServiceException();
        }

        return ProjectConvert.INSTANCE.toDTO(project);
    }

    public PageData<ProjectPageVO> getPage(ProjectPageDTO dto) {
        Page<ProjectEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<ProjectEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.likeIfPresent(ProjectEntity::getName, dto.getName());
        wrapperPlus.eqIfPresent(ProjectEntity::getStatus, dto.getStatus());
        wrapperPlus.orderByDesc(ProjectEntity::getCreateTime);

        projectDao.selectPage(page, wrapperPlus);
        List<ProjectPageVO> list = page.getRecords()
                .stream()
                .map(ProjectConvert.INSTANCE::toPageVO)
                .toList();

        return new PageData<>(page, list);
    }

    public List<ProjectListVO> getList() {
        LambdaQueryWrapper<ProjectEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProjectEntity::getStatus, ProjectEntity::getCreateTime);
        List<ProjectEntity> list = projectDao.selectList(wrapper);
        return list
                .stream()
                .map(ProjectConvert.INSTANCE::toListVO)
                .toList();
    }

}
