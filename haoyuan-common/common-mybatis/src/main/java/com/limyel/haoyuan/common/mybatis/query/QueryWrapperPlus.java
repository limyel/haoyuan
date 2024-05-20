package com.limyel.haoyuan.common.mybatis.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class QueryWrapperPlus<T> extends QueryWrapper<T> {

    public QueryWrapperPlus<T> likeIfPresent(String field, String value) {
        if (StringUtils.hasText(value)) {
            return (QueryWrapperPlus<T>) super.like(field, value);
        }
        return this;
    }

    public QueryWrapperPlus<T> inIfPresent(String field, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (QueryWrapperPlus<T>) super.in(field, values);
        }
        return this;
    }

    public QueryWrapperPlus<T> inIfPresent(String field, Object... values) {
        if (values != null && values.length > 0) {
            return (QueryWrapperPlus<T>) super.in(field, values);
        }
        return this;
    }

    public QueryWrapperPlus<T> eqIfPresent(String field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (QueryWrapperPlus<T>) super.eq(field, value);
        }
        return this;
    }

    public QueryWrapperPlus<T> neIfPresent(String field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (QueryWrapperPlus<T>) super.ne(field, value);
        }
        return this;
    }

    public QueryWrapperPlus<T> gtIfPresent(String field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (QueryWrapperPlus<T>) super.gt(field, value);
        }
        return this;
    }

    public QueryWrapperPlus<T> geIfPresent(String field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (QueryWrapperPlus<T>) super.ge(field, value);
        }
        return this;
    }

    public QueryWrapperPlus<T> ltIfPresent(String field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (QueryWrapperPlus<T>) super.lt(field, value);
        }
        return this;
    }

    public QueryWrapperPlus<T> leIfPresent(String field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (QueryWrapperPlus<T>) super.le(field, value);
        }
        return this;
    }

    public QueryWrapperPlus<T> betweenIfPresent(String field, Object value1, Object value2) {
        if (!ObjectUtils.isEmpty(value1) && !ObjectUtils.isEmpty(value1)) {
            return (QueryWrapperPlus<T>) super.between(field, value1, value2);
        }
        if (!ObjectUtils.isEmpty(value1)) {
            return (QueryWrapperPlus<T>) ge(field, value1);
        }
        if (!ObjectUtils.isEmpty(value2)) {
            return (QueryWrapperPlus<T>) le(field, value2);
        }
        return this;
    }

    public QueryWrapperPlus<T> betweenIfPresent(String field, Object[] values) {
        Object value1 = values.length > 0 ? values[0] : null;
        Object value2 = values.length > 1 ? values[1] : null;
        return betweenIfPresent(field, value1, value2);
    }

}