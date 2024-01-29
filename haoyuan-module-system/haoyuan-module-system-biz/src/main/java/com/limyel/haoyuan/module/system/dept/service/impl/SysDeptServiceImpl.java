package com.limyel.haoyuan.module.system.dept.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.dept.convert.SysDeptConvert;
import com.limyel.haoyuan.module.system.dept.dao.SysDeptDao;
import com.limyel.haoyuan.module.system.dept.dataobject.SysDeptDO;
import com.limyel.haoyuan.module.system.dept.dto.SysDeptDTO;
import com.limyel.haoyuan.module.system.dept.dto.SysDeptFilterDTO;
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
            dto.setPid(SysDeptDO.PID_ROOT);
        }

        validatePid(null, dto.getPid());
        validateNameUnique(null, dto.getPid(), dto.getName());

        SysDeptDO sysDept = SysDeptConvert.INSTANCE.toEntity(dto);
        sysDeptDao.insert(sysDept);

        return sysDept.getId();
    }

    @Override
    public void update(SysDeptDTO dto) {
        if (dto.getPid() == null) {
            dto.setPid(SysDeptDO.PID_ROOT);
        }

        validateExist(dto.getId());
        validatePid(dto.getId(), dto.getPid());
        validateNameUnique(dto.getId(), dto.getPid(), dto.getName());

        SysDeptDO sysDept = SysDeptConvert.INSTANCE.toEntity(dto);
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
    public SysDeptDO get(Long id) {
        return sysDeptDao.selectById(id);
    }

    @Override
    public PageData<SysDeptDO> getPage(SysDeptFilterDTO dto) {
        Page<SysDeptDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<SysDeptDO> wrapperPlus = new LambdaQueryWrapperPlus<SysDeptDO>()
                .likeIfPresent(SysDeptDO::getName, dto.getName())
                .eqIfPresent(SysDeptDO::getStatus, dto.getStatus())
                .orderByAsc(SysDeptDO::getSort);
        sysDeptDao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

    @Override
    public List<SysDeptDO> getList(SysDeptFilterDTO dto) {
        return sysDeptDao.selectList(dto);
    }

    /**
     * 校验上级部门有效性
     * @param id ID
     * @param pid 上级部门ID
     */
    private void validatePid(Long id, Long pid) {
        if (pid == null || SysDeptDO.PID_ROOT.equals(pid)) {
            return;
        }
        // 1. 上级部门不能是本身
        if (Objects.equals(id, pid)) {
            throw new BizException(SysErrorCodeConstant.DEPT_PARENT_ERROR);
        }
        // 2. 上级部门不存在
        SysDeptDO parent = sysDeptDao.selectById(pid);
        if (parent == null) {
            throw new BizException(SysErrorCodeConstant.DEPT_PARENT_NOT_FOUND);
        }
        // 3. 检查上级部门是不是子部门
        // 新增部门
        if (id == null) {
            return;
        }
        pid = parent.getPid();
        while (!SysDeptDO.PID_ROOT.equals(pid) && pid != null) {
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
        SysDeptDO sysDept = sysDeptDao.selectByPidAndName(pid, name);
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
        SysDeptDO sysDept = sysDeptDao.selectById(id);
        if (sysDept == null) {
            throw new BizException(SysErrorCodeConstant.DEPT_NOT_FOUND);
        }
    }


}
