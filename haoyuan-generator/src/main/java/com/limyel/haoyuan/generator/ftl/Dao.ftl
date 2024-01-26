package ${project}.module.${module}.${package}.dao;

import ${project}.framework.mybatis.dao.BaseDao;
import ${project}.framework.mybatis.query.LambdaQueryWrapperPlus;
import ${project}.module.${module}.${package}.dto.${beanName}FilterDTO;
import ${project}.module.${module}.${package}.entity.${beanName}Entity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ${beanName}Dao extends BaseDao<${beanName}Entity> {

    default List<${beanName}Entity> selectList(${beanName}FilterDTO req) {
        return selectList(new LambdaQueryWrapperPlus<${beanName}Entity>();
    }

}
