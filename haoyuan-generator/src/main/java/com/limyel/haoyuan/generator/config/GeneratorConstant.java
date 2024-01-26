package com.limyel.haoyuan.generator.config;

public interface GeneratorConstant {

    String SQL_SHOW_TABLE_STATUS = "SHOW TABLE STATUS";
    String SQL_SHOW_TABLE_FIELDS = "SHOW FULL FIELDS FROM %s";
    String SQL_SHOW_TABLE_INDEX = "SHOW INDEX FROM %s";


    String[] SQL_LOCAL_DATE_TIME_TYPE = new String[]{"datetime", "timestamp"};
    String[] SQL_LOCAL_DATE_TYPE = new String[]{"date"};
    String[] SQL_DECIMAL_TYPE = new String[]{"decimal", "double", "float"};
    String[] SQL_STRING_TYPE = new String[]{"char", "varchar", "text", "mediumtext", "longtext"};
    String[] SQL_INTEGER_TYPE = new String[]{"int", "tinyint"};
    String[] SQL_LONG_TYPE = new String[]{"bigint"};
    String[] SQL_BOOLEAN_TYPE = new String[]{"bit"};



}
