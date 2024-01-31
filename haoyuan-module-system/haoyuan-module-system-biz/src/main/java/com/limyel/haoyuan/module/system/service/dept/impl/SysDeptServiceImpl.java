package com.limyel.haoyuan.module.system.service.dept.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.convert.dept.DeptConvert;
import com.limyel.haoyuan.module.system.dao.dept.SysDeptDao;
import com.limyel.haoyuan.module.system.dataobject.dept.DeptDO;
import com.limyel.haoyuan.module.system.dto.dept.DeptDTO;
import com.limyel.haoyuan.module.system.dto.dept.DeptPageDTO;
import com.limyel.haoyuan.module.system.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysDeptServiceImpl implements DeptService {

    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public Long create(DeptDTO dto) {
        if (dto.getPid() == null) {
            dto.setPid(DeptDO.PID_ROOT);
        }

        validatePid(null, dto.getPid());
        validateNameUnique(null, dto.getPid(), dto.getName());

        DeptDO sysDept = DeptConvert.INSTANCE.toEntity(dto);
        sysDeptDao.insert(sysDept);

        return sysDept.getId();
    }

    @Override
    public void update(DeptDTO dto) {
        if (dto.getPid() == null) {
            dto.setPid(DeptDO.PID_ROOT);
        }

        validateExist(dto.getId());
        validatePid(dto.getId(), dto.getPid());
        validateNameUnique(dto.getId(), dto.getPid(), dto.getName());

        DeptDO sysDept = DeptConvert.INSTANCE.toEntity(dto);
        sysDeptDao.updateById(sysDept);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);

        if (sysDeptDao.selectCountByPid(id) > 0) {
            throw new BizException(SysErrorCodeConstant.DEPT_HAS_CHILDREN);
        }

        sysDeptDao.deleteById(id);
    }

    @Override
    public DeptDO get(Long id) {
        return sysDeptDao.selectById(id);
    }

    @Override
    public PageData<DeptDO> getPage(DeptPageDTO dto) {
        Page<DeptDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<DeptDO> wrapperPlus = new LambdaQueryWrapperPlus<DeptDO>()
                .likeIfPresent(DeptDO::getName, dto.getName())
                .eqIfPresent(DeptDO::getStatus, dto.getStatus())
                .orderByAsc(DeptDO::getSort);
        sysDeptDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    @Override
    public List<DeptDO> getList(DeptPageDTO dto) {
        return sysDeptDao.selectList(dto);
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
            throw new BizException(SysErrorCodeConstant.DEPT_PARENT_ERROR);
        }
        // 2. 上级部门不存在
        DeptDO parent = sysDeptDao.selectById(pid);
        if (parent == null) {
            throw new BizException(SysErrorCodeConstant.DEPT_PARENT_NOT_FOUND);
        }
        // 3. 检查上级部门是不是子部门
        // 新增部门
        if (id == null) {
            return;
        }
        pid = parent.getPid();
        while (!DeptDO.PID_ROOT.equals(pid) && pid != null) {
            if (Objects.equals(id, pid)) {
                throw new BizException(SysErrorCodeConstant.DEPT_PARENT_IS_CHILD);
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
        DeptDO sysDept = sysDeptDao.selectByPidAndName(pid, name);
        if (sysDept == null) {
            return;
        }
        // 新增时重复
        if (id == null) {
            throw new BizException(SysErrorCodeConstant.DEPT_NAME_DUPLICATE);
        }
        // 更新时重复
        if (!Objects.equals(id, sysDept.getId())) {
            throw new BizException(SysErrorCodeConstant.DEPT_NAME_DUPLICATE);
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
        DeptDO sysDept = sysDeptDao.selectById(id);
        if (sysDept == null) {
            throw new BizException(SysErrorCodeConstant.DEPT_NOT_FOUND);
        }
    }


}
