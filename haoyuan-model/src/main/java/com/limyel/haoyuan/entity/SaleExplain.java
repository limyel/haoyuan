package com.limyel.haoyuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 售货信息扩展
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("sale_explain")
public class SaleExplain implements Serializable {

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
     * 是否固定
     */
    private Integer isFixed;

    /**
     * 内容
     */
    private String content;

    /**
     * 位置
     */
    private Integer sequence;

    private Long replaceId;

    /**
     * spu id
     */
    private Long spuId;


}
