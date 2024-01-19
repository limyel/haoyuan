package com.limyel.haoyuan.generator.bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     * 参数名称
     */
    private String beanParamName;

    /**
     * 注释
     */
    private String comment;

    /**
     * 字段
     */
    private List<Field> fieldList;

    /**
     * 唯一索引集合
     */
    private Map<String, List<Field>> keyIndexMap = new LinkedHashMap<>();

    /**
     * 是否含有
     */
    private Boolean haveLocalDateTime = false;

    private Boolean haveBigDecimal = false;

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

    public String getBeanParamName() {
        return beanParamName;
    }

    public void setBeanParamName(String beanParamName) {
        this.beanParamName = beanParamName;
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

    public Map<String, List<Field>> getKeyIndexMap() {
        return keyIndexMap;
    }

    public void setKeyIndexMap(Map<String, List<Field>> keyIndexMap) {
        this.keyIndexMap = keyIndexMap;
    }

    public Boolean getHaveLocalDateTime() {
        return haveLocalDateTime;
    }

    public void setHasLocalDateTime(Boolean haveDateTime) {
        this.haveLocalDateTime = haveDateTime;
    }

    public Boolean getHaveBigDecimal() {
        return haveBigDecimal;
    }

    public void setHaveBigDecimal(Boolean haveBigDecimal) {
        this.haveBigDecimal = haveBigDecimal;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", beanName='" + beanName + '\'' +
                ", beanParamName='" + beanParamName + '\'' +
                ", comment='" + comment + '\'' +
                ", fieldList=" + fieldList +
                ", keyIndexMap=" + keyIndexMap +
                ", haveLocalDateTime=" + haveLocalDateTime +
                ", haveBigDecimal=" + haveBigDecimal +
                '}';
    }
}
