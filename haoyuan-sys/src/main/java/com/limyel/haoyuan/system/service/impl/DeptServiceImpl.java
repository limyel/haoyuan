package com.limyel.haoyuan.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.DeptConvert;
import com.limyel.haoyuan.system.dao.DeptDao;
import com.limyel.haoyuan.system.domain.DeptDO;
import com.limyel.haoyuan.system.dto.dept.DeptDTO;
import com.limyel.haoyuan.system.dto.dept.DeptPageDTO;
import com.limyel.haoyuan.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public Long create(DeptDTO dto) {
        if (dto.getPid() == null) {
            dto.setPid(DeptDO.PID_ROOT);
        }

        validatePid(null, dto.getPid());
        validateNameUnique(null, dto.getPid(), dto.getName());

        DeptDO dept = DeptConvert.INSTANCE.toEntity(dto);
        deptDao.insert(dept);

        return dept.getId();
    }

    @Override
    public void update(DeptDTO dto) {
        if (dto.getPid() == null) {
            dto.setPid(DeptDO.PID_ROOT);
        }

        validateExist(dto.getId());
        validatePid(dto.getId(), dto.getPid());
        validateNameUnique(dto.getId(), dto.getPid(), dto.getName());

        DeptDO dept = DeptConvert.INSTANCE.toEntity(dto);
        deptDao.updateById(dept);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);

        if (deptDao.selectCountByPid(id) > 0) {
            throw new BizException(SysErrorCode.DEPT_HAS_CHILDREN);
        }

        deptDao.deleteById(id);
    }

    @Override
    public DeptDO get(Long id) {
        return deptDao.selectById(id);
    }

    @Override
    public PageData<DeptDO> getPage(DeptPageDTO dto) {
        Page<DeptDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<DeptDO> wrapperPlus = new LambdaQueryWrapperPlus<DeptDO>()
                .likeIfPresent(DeptDO::getName, dto.getName())
                .eqIfPresent(DeptDO::getStatus, dto.getStatus())
                .orderByAsc(DeptDO::getSort);
        deptDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    @Override
    public List<DeptDO> getList(DeptPageDTO dto) {
        return deptDao.selectList(dto);
    }

    /**
     * 校验上级部门有效性
     * @param id ID
     * @param pid 上级部门ID
     */
    private void validatePid(Long id, Long pid) {
        if (pid == null || DeptDO.PID_ROOT.equals(pid)) {
            return;
        }
        // 1. 上级部门不能是本身
        if (Objects.equals(id, pid)) {
            throw new BizException(SysErrorCode.DEPT_PARENT_ERROR);
        }
        // 2. 上级部门不存在
        DeptDO parent = deptDao.selectById(pid);
        if (parent == null) {
            throw new BizException(SysErrorCode.DEPT_PARENT_NOT_FOUND);
        }
        // 3. 检查上级部门是不是子部门
        // 新增部门
        if (id == null) {
            return;
        }
        pid = parent.getPid();
        while (!DeptDO.PID_ROOT.equals(pid) && pid != null) {
            if (Objects.equals(id, pid)) {
                throw new BizException(SysErrorCode.DEPT_PARENT_IS_CHILD);
            }
            parent = deptDao.selectById(pid);
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
        DeptDO dept = deptDao.selectByPidAndName(pid, name);
        if (dept == null) {
            return;
        }
        // 新增时重复
        if (id == null) {
            throw new BizException(SysErrorCode.DEPT_NAME_DUPLICATE);
        }
        // 更新时重复
        if (!Objects.equals(id, dept.getId())) {
            throw new BizException(SysErrorCode.DEPT_NAME_DUPLICATE);
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
        DeptDO dept = deptDao.selectById(id);
        if (dept == null) {
            throw new BizException(SysErrorCode.DEPT_NOT_FOUND);
        }
    }


}