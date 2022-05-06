package com.limyel.haoyuan.portal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 主题
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
public class Theme implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Boolean deleted;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 名称
     */
    private String name;

    /**
     * 模板名称
     */
    private String tplName;

    /**
     * 入口图片
     */
    private String entranceImg;

    /**
     * 扩展
     */
    private String extend;

    /**
     * 内容顶部图片
     */
    private String internalTopImg;

    /**
     * 标题图片
     */
    private String titleImg;

    /**
     * 是否上线
     */
    @TableField(value = "is_online")
    private Boolean online;


}
