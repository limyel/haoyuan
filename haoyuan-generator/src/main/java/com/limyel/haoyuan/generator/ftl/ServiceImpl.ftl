package ${project}.module.${module}.${packet}.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${project}.common.exception.ServiceException;
import ${project}.framework.mybatis.pojo.PageData;
import ${project}.framework.mybatis.query.LambdaQueryWrapperPlus;
import ${project}.module.${module}.constant.SysErrorCodeConstant;
import ${project}.module.${module}.${packet}.convert.${beanName}Convert;
import ${project}.module.${module}.${packet}.dao.${beanName}Dao;
import ${project}.module.${module}.${packet}.dto.${beanName}DTO;
import ${project}.module.${module}.${packet}.dto.${beanName}FilterDTO;
import ${project}.module.${module}.${packet}.entity.${beanName}Entity;
import ${project}.module.${module}.${packet}.service.${beanName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ${beanName}ServiceImpl implements ${beanName}Service {

    @Autowired
    private ${beanName}Dao ${beanInstanceName}Dao;

    @Override
    public Long create(${beanName}DTO dto) {
        validateNameUnique(null, dto.getName());
        validateCodeUnique(null, dto.getCode());

        ${beanName}Entity ${beanInstanceName} = ${beanName}Convert.INSTANCE.toEntity(dto);
        ${beanInstanceName}Dao.insert(${beanInstanceName});

        return ${beanInstanceName}.getId();
    }

    @Override
    public void update(${beanName}DTO dto) {
        validateExist(dto.getId());
        validateNameUnique(dto.getId(), dto.getName());
        validateCodeUnique(dto.getId(), dto.getCode());

        ${beanName}Entity ${beanInstanceName} = ${beanName}Convert.INSTANCE.toEntity(dto);
        ${beanInstanceName}Dao.updateById(${beanInstanceName});
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        ${beanInstanceName}Dao.deleteById(id);
    }

    @Override
    public ${beanName}Entity get(Long id) {
        return ${beanInstanceName}Dao.selectById(id);
    }

    @Override
    public List<${beanName}Entity> getList(${beanName}FilterDTO dto) {
        return ${beanInstanceName}Dao.selectList(dto);
    }

    @Override
    public PageData<${beanName}Entity> getPage(${beanName}FilterDTO dto) {
        Page<${beanName}Entity> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<${beanName}Entity> wrapperPlus = new LambdaQueryWrapperPlus<${beanName}Entity>()
                .likeIfPresent(${beanName}Entity::getName, dto.getName())
                .eqIfPresent(${beanName}Entity::getStatus, dto.getStatus())
                .orderByAsc(${beanName}Entity::getSort);
                ${beanInstanceName}Dao.selectPage(page, wrapperPlus);
        return new PageData<>(page);
    }

}
