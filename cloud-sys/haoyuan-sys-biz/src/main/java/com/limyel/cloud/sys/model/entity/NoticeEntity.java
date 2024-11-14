package com.limyel.cloud.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("notice")
public class NoticeEntity extends BaseEntity {

    /**
     * 标题
     */
    private String title;

    /**
     * 公告类型，1-通知 2-公告
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态，0-正常 1-关闭
     */
    private Integer status;

}
