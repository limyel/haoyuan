package com.limyel.haoyuan.framework.mybatis.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TenantBaseDO extends BaseDO {

    private Long tenantId;

}
