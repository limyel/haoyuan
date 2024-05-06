package com.limyel.blog.common.mybatis.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TenantBaseDO extends BaseDO {

    private Long tenantId;

}
