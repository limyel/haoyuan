package com.limyel.haoyuan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * spu标签
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("spu_tag")
public class SpuTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spu id
     */
    private Long spuId;

    /**
     * 标签 id
     */
    private Long tagId;


}
