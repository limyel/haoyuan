package com.limyel.blog.common.mybatis.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class LambdaQueryWrapperPlus<T> extends LambdaQueryWrapper<T> {

    public LambdaQueryWrapperPlus<T> likeIfPresent(SFunction<T, ?> field, String value) {
        if (StringUtils.hasText(value)) {
            return (LambdaQueryWrapperPlus<T>) super.like(field, value);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> inIfPresent(SFunction<T, ?> field, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (LambdaQueryWrapperPlus<T>) super.in(field, values);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> inIfPresent(SFunction<T, ?> field, Object... values) {
        if (values != null && values.length > 0) {
            return (LambdaQueryWrapperPlus<T>) super.in(field, values);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> eqIfPresent(SFunction<T, ?> field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (LambdaQueryWrapperPlus<T>) super.eq(field, value);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> neIfPresent(SFunction<T, ?> field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (LambdaQueryWrapperPlus<T>) super.ne(field, value);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> gtIfPresent(SFunction<T, ?> field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (LambdaQueryWrapperPlus<T>) super.gt(field, value);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> geIfPresent(SFunction<T, ?> field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (LambdaQueryWrapperPlus<T>) super.ge(field, value);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> ltIfPresent(SFunction<T, ?> field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (LambdaQueryWrapperPlus<T>) super.lt(field, value);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> leIfPresent(SFunction<T, ?> field, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return (LambdaQueryWrapperPlus<T>) super.le(field, value);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> betweenIfPresent(SFunction<T, ?> field, Object value1, Object value2) {
        if (!ObjectUtils.isEmpty(value1) && !ObjectUtils.isEmpty(value1)) {
            return (LambdaQueryWrapperPlus<T>) super.between(field, value1, value2);
        }
        if (!ObjectUtils.isEmpty(value1)) {
            return (LambdaQueryWrapperPlus<T>) ge(field, value1);
        }
        if (!ObjectUtils.isEmpty(value2)) {
            return (LambdaQueryWrapperPlus<T>) le(field, value2);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> betweenIfPresent(SFunction<T, ?> field, Object[] values) {
        Object value1 = values.length > 0 ? values[0] : null;
        Object value2 = values.length > 1 ? values[1] : null;
        return betweenIfPresent(field, value1, value2);
    }

    @Override
    public LambdaQueryWrapperPlus<T> orderByAsc(SFunction<T, ?> column) {
        super.orderByAsc(column);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> orderByDesc(SFunction<T, ?> column) {
        super.orderByDesc(column);
        return this;
    }

}
