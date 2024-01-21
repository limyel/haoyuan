package ${project}.module.${module}.${package}.service;

import ${project}.framework.mybatis.pojo.PageData;
import ${project}.module.${module}.${package}.dto.${beanName}DTO;
import ${project}.module.${module}.${package}.dto.${beanName}FilterDTO;
import ${project}.module.${module}.${package}.entity.${beanName}Entity;

import java.util.List;

public interface ${beanName}Service {

    Long create(${beanName}DTO dto);

    void update(${beanName}DTO dto);

    void delete(Long id);

    ${beanName}Entity get(Long id);

    List<${beanName}Entity> getList(${beanName}FilterDTO dto);

    PageData<${beanName}Entity> getPage(${beanName}FilterDTO dto);

}
