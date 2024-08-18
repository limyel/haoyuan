package com.limyel.haoyuan.blog.sync.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sync_record")
public class RecordEntity extends BaseEntity {

    /**
     * 积分
     */
    private Long point;

    /**
     * 原因
     */
    private String reason;

    /**
     * 用户ID
     */
    private String username;

    /**
     * 状态，0-未同步 1-已同步
     */
    private Integer status;

}
