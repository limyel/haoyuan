package com.limyel.haoyuan.generator.bean;

import java.util.*;

public class Table {

    /**
     * 表名
     */
    private String tableName;

    /**
     * bean 名称
     */
    private String beanName;

    /**
     * bean 实例名称
     */
    private String beanInstanceName;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 路由
     */
    private String route;

    /**
     * 文件名后缀
     */
    private String fileSuffix;

    /**
     * 注释
     */
    private String comment;

    /**
     * 字段
     */
    private List<Field> fieldList;

    private Boolean tenant;

    /**
     * 唯一索引集合
     */
    private Map<String, List<Field>> keyIndexMap = new LinkedHashMap<>();

    /**
     * 是否含有
     */
    private Boolean hasLocalDateTime = false;

    private Boolean hasBigDecimal = false;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanInstanceName() {
        return beanInstanceName;
    }

    public void setBeanInstanceName(String beanInstanceName) {
        this.beanInstanceName = beanInstanceName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public Boolean getTenant() {
        return tenant;
    }

    public void setTenant(Boolean tenant) {
        this.tenant = tenant;
    }

    public Map<String, List<Field>> getKeyIndexMap() {
        return keyIndexMap;
    }

    public void setKeyIndexMap(Map<String, List<Field>> keyIndexMap) {
        this.keyIndexMap = keyIndexMap;
    }

    public Boolean getHasLocalDateTime() {
        return hasLocalDateTime;
    }

    public void setHasLocalDateTime(Boolean hasLocalDateTime) {
        this.hasLocalDateTime = hasLocalDateTime;
    }

    public Boolean getHasBigDecimal() {
        return hasBigDecimal;
    }

    public void setHasBigDecimal(Boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
    }
}
