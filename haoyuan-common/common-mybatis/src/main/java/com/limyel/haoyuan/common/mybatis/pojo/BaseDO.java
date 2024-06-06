package com.limyel.haoyuan.common.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public abstract class BaseDO {

    @TableId
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableLogic
    private LocalDateTime deleteTime;

    public void validateUnique(Long id, ErrorCode errorCode) {
        if (id == null) {
            throw new BizException(errorCode);
        }
        if (!Objects.equals(this.id, id)) {
            throw new BizException(errorCode);
        }
    }

}