package com.limyel.haoyuan.framework.mybatis.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TenantBaseEntity extends BaseEntity{

    private Long tenantId;

}
