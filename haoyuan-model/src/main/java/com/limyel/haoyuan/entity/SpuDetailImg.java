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
 * spu详情图片
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("spu_detail_img")
public class SpuDetailImg implements Serializable {

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
     * 图片
     */
    private String img;

    /**
     * spu id
     */
    private Long spuId;

    /**
     * 位置
     */
    private Integer sequence;


}
