package com.limyel.haoyuan.module.system.dept.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.ServiceException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.dept.convert.SysDeptConvert;
import com.limyel.haoyuan.module.system.dept.dao.SysDeptDao;
import com.limyel.haoyuan.module.system.dept.dto.SysDeptDTO;
import com.limyel.haoyuan.module.system.dept.dto.req.SysDeptFilterReq;
import com.limyel.haoyuan.module.system.dept.entity.SysDeptEntity;
import com.limyel.haoyuan.module.system.dept.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public Long create(SysDeptDTO dto) {
        if (dto.getPid() == null) {
            dto.setPid(SysDeptEntity.PID_ROOT);
        }

        validatePid(null, dto.getPid());
        validateNameUnique(null, dto.getPid(), dto.getName());

        SysDeptEntity sysDept = SysDeptConvert.INSTANCE.toEntity(dto);
        sysDeptDao.insert(sysDept);

        return sysDept.getId();
    }

    @Override
    public void update(SysDeptDTO dto) {
        if (dto.getPid() == null) {
            dto.setPid(SysDeptEntity.PID_ROOT);
        }

        validateExist(dto.getId());
        validatePid(dto.getId(), dto.getPid());
        validateNameUnique(dto.getId(), dto.getPid(), dto.getName());

        SysDeptEntity sysDept = SysDeptConvert.INSTANCE.toEntity(dto);
        sysDeptDao.updateById(sysDept);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);

        if (sysDeptDao.selectCountByPid(id) > 0) {
            throw new ServiceException(SysErrorCodeConstant.DEPT_HAS_CHILDREN);
        }

        sysDeptDao.deleteById(id);
    }

    @Override
    public SysDeptEntity get(Long id) {
        return sysDeptDao.selectById(id);
    }

    @Override
    public PageData<SysDeptEntity> getPage(SysDeptFilterReq req) {
        Page<SysDeptEntity> page = new Page<>(req.getPageNum(), req.getPageSize());
        LambdaQueryWrapperPlus<SysDeptEntity> wrapperPlus = new LambdaQueryWrapperPlus<SysDeptEntity>()
                .likeIfPresent(SysDeptEntity::getName, req.getName())
                .eqIfPresent(SysDeptEntity::getStatus, req.getStatus())
                .orderByAsc(SysDeptEntity::getSort);
        sysDeptDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    @Override
    public List<SysDeptEntity> getList(SysDeptFilterReq req) {
        return sysDeptDao.selectList(req);
    }

    /**
     * 校验上级部门有效性
     * @param id ID
     * @param pid 上级部门ID
     */
    private void validatePid(Long id, Long pid) {
        if (pid == null || SysDeptEntity.PID_ROOT.equals(pid)) {
            return;
        }
        // 1. 上级部门不能是本身
        if (Objects.equals(id, pid)) {
            throw new ServiceException(SysErrorCodeConstant.DEPT_PARENT_ERROR);
        }
        // 2. 上级部门不存在
        SysDeptEntity parent = sysDeptDao.selectById(pid);
        if (parent == null) {
            throw new ServiceException(SysErrorCodeConstant.DEPT_PARENT_NOT_FOUND);
        }
        // 3. 检查上级部门是不是子部门
        // 新增部门
        if (id == null) {
            return;
        }
        pid = parent.getPid();
        while (!SysDeptEntity.PID_ROOT.equals(pid) && pid != null) {
            if (Objects.equals(id, pid)) {
                throw new ServiceException(SysErrorCodeConstant.DEPT_PARENT_IS_CHILD);
            }
            parent = sysDeptDao.selectById(pid);
            if (parent == null) {
                break;
            }
            pid = parent.getPid();
        }
    }

    /**
     * 校验部门名称唯一性
     * @param id ID
     * @param pid 上级部门ID
     * @param name 部门名称
     */
    private void validateNameUnique(Long id, Long pid, String name) {
        SysDeptEntity sysDept = sysDeptDao.selectByPidAndName(pid, name);
        if (sysDept == null) {
            return;
        }
        // 新增时重复
        if (id == null) {
            throw new ServiceException(SysErrorCodeConstant.DEPT_NAME_DUPLICATE);
        }
        // 更新时重复
        if (!Objects.equals(id, sysDept.getId())) {
            throw new ServiceException(SysErrorCodeConstant.DEPT_NAME_DUPLICATE);
        }
    }

    /**
     * 校验部门存在
     * @param id ID
     */
    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        SysDeptEntity sysDept = sysDeptDao.selectById(id);
        if (sysDept == null) {
            throw new ServiceException(SysErrorCodeConstant.DEPT_NOT_FOUND);
        }
    }


}
