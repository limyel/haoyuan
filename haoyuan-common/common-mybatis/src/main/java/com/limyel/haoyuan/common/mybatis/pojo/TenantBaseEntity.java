package com.limyel.haoyuan.common.mybatis.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TenantBaseEntity extends BaseEntity {

    private Long tenantId;

}