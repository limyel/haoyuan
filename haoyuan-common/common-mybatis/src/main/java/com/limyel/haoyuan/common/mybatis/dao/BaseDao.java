package com.limyel.haoyuan.common.mybatis.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.IBaseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T extends IBaseEntity> extends BaseMapper<T> {

    // 批量插入
    int insertBatchSomeColumn(@Param("list") List<T> batchList);

    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default Long selectCount() {
        return selectCount(new QueryWrapper<>());
    }

    default Long selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value));
    }

    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(String field, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return new ArrayList<>();
        }
        return selectList(new QueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return new ArrayList<>();
        }
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    default int delete(SFunction<T, ?> field, Object value) {
        return delete(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default int insertOrUpdate(T domain) {
        return Db.saveOrUpdate(domain) ? 1 : 0;
    }

    default <F> void validateUnique(Long id, SFunction<T, F> field, F value, String errorMsg) {
        T item = selectOne(field, value);
        if (item != null) {
            if (id == null) {
                throw new ServiceException(errorMsg);
            }
            if (!item.getId().equals(id)) {
                throw new ServiceException(errorMsg);
            }
        }
    }

    default void validateExist(Long id, String errorMsg) {
        if (id == null) {
            return;
        }
        T item = selectById(id);
        if (item == null) {
            throw new ServiceException(errorMsg);
        }
    }

}