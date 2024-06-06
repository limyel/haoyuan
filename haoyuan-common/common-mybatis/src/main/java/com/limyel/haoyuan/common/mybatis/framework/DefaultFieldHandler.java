package com.limyel.haoyuan.common.mybatis.framework;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

public class DefaultFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (!ObjectUtils.isEmpty(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();

            LocalDateTime now = LocalDateTime.now();
            if (ObjectUtils.isEmpty(baseDO.getCreateTime())) {
                baseDO.setCreateTime(now);
            }
            if (ObjectUtils.isEmpty(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(now);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (ObjectUtils.isEmpty(updateTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

        Object updaterId = getFieldValByName("updaterId", metaObject);
        // todo userId
    }
}