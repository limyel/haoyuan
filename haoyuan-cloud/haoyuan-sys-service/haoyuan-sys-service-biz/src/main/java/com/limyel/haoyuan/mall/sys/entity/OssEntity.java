package com.limyel.haoyuan.mall.sys.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_oss")
public class OssEntity extends BaseEntity {

    private String url;

}
