package ${project}.module.${module}.${package}.entity;

<#if tenant>
import ${project}.framework.mybatis.pojo.TenantBaseEntity;
<#else>
import ${project}.framework.mybatis.pojo.BaseEntity;
</#if>

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
<#if tenant>
public class ${beanName}Entity extends TenantBaseEntity {
<#else>
public class ${beanName}Entity extends BaseEntity {
</#if>

    <#list fields as field>
    private ${field.javaType} ${field.fieldName};

    </#list>
}
