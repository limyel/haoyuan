package com.limyel.haoyuan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * spu规格名关联表
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("spu_key")
public class SpuKey implements Serializable {

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
     * spec_key id
     */
    private Long specKeyId;


}
