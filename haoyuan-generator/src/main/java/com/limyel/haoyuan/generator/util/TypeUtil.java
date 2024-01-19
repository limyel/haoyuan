package com.limyel.haoyuan.generator.util;

import com.limyel.haoyuan.generator.config.GeneratorConstant;
import org.apache.commons.lang3.ArrayUtils;

public class TypeUtil {

    public static String sqlTypeToJavaType(String type) {
        if (type.contains("(")) {
            type = type.substring(0, type.indexOf('('));
        }
        if (ArrayUtils.contains(GeneratorConstant.SQL_INTEGER_TYPE, type)) {
            return "Integer";
        } else if (ArrayUtils.contains(GeneratorConstant.SQL_LONG_TYPE, type)) {
            return "Long";
        } else if (ArrayUtils.contains(GeneratorConstant.SQL_STRING_TYPE, type)) {
            return "String";
        } else if (ArrayUtils.contains(GeneratorConstant.SQL_LOCAL_DATE_TIME_TYPE, type)) {
            return "LocalDateTime";
        } else if (ArrayUtils.contains(GeneratorConstant.SQL_LOCAL_DATE_TYPE, type)) {
            return "LocalDate";
        } else if (ArrayUtils.contains(GeneratorConstant.SQL_DECIMAL_TYPE, type)) {
            return "BigDecimal";
        } else if (ArrayUtils.contains(GeneratorConstant.SQL_BOOLEAN_TYPE, type)) {
            return "Boolean";
        } else {
            throw new RuntimeException("无法识别类型: " + type);
        }
    }

    public static Boolean isLocalDateTime(String javaType) {
        return "LocalDateTime".equals(javaType);
    }

    public static Boolean isBigDecimal(String javaType) {
        return "BigDecimal".equals(javaType);
    }

}
