package com.limyel.haoyuan.generator.builder;

import com.limyel.haoyuan.generator.bean.Field;
import com.limyel.haoyuan.generator.bean.Table;
import com.limyel.haoyuan.generator.config.Config;
import com.limyel.haoyuan.generator.util.PropertiesUtil;
import com.limyel.haoyuan.generator.config.GeneratorConstant;
import com.limyel.haoyuan.generator.util.StringUtil;
import com.limyel.haoyuan.generator.util.TypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TableBuilder.class);

    private static Connection connection = null;

    static {
        String driverName = PropertiesUtil.getString("db.driver.name");
        String url = PropertiesUtil.getString("db.url");
        String username = PropertiesUtil.getString("db.username");
        String password = PropertiesUtil.getString("db.password");

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            logger.error("数据库连接失败。");
        }
    }

    public static List<Table> getTables() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Table> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(GeneratorConstant.SQL_SHOW_TABLE_STATUS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tableName = resultSet.getString("name");

                if (Config.TABLE_EXCLUDE_LIST.contains(tableName)) {
                    continue;
                }

                String beanName = tableName;
                if (Config.TABLE_IGNORE_PREFIX) {
                    beanName = tableName.substring(beanName.indexOf("_") + 1);
                }
                beanName = StringUtil.underlineToCamel(beanName, true);

                String comment = resultSet.getString("comment");

                Table table = new Table();
                table.setTableName(tableName);
                table.setBeanName(beanName);
                table.setComment(comment);
//                table.setBeanNameSuffix(beanName + Config.SUFFIX_BEAN_PARAM);

                setFields(table);
                setKeyIndex(table);
                logger.info("table: {}", table);

                result.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }

    private static void setFields(Table table) {
        PreparedStatement preparedStatement = null;
        ResultSet fieldResult = null;
        List<Field> result = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(String.format(GeneratorConstant.SQL_SHOW_TABLE_FIELDS, table.getTableName()));
            fieldResult = preparedStatement.executeQuery();
            while (fieldResult.next()) {
                String fieldName = fieldResult.getString("field");
                String propertyName = StringUtil.underlineToCamel(fieldName, false);
                String comment = fieldResult.getString("comment");
                String sqlType = fieldResult.getString("type");
                String javaType = TypeUtil.sqlTypeToJavaType(sqlType);
                Boolean autoIncrement = fieldResult.getString("extra").contains("auto_increment");

                Field field = new Field();
                field.setFieldName(fieldName);
                field.setPropertyName(propertyName);
                field.setSqlType(sqlType);
                field.setJavaType(javaType);
                field.setComment(comment);
                field.setAutoIncrement(autoIncrement);
                if (!table.getHasLocalDateTime() && TypeUtil.isLocalDateTime(javaType)) {
                    table.setHasLocalDateTime(true);
                }
                if (!table.getHasBigDecimal() && TypeUtil.isBigDecimal(javaType)) {
                    table.setHasBigDecimal(true);
                }

                result.add(field);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fieldResult != null) {
                try {
                    fieldResult.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        table.setFieldList(result);
    }

    private static void setKeyIndex(Table table) {
        PreparedStatement preparedStatement = null;
        ResultSet indexResult = null;
        List<Field> result = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(String.format(GeneratorConstant.SQL_SHOW_TABLE_INDEX, table.getTableName()));
            indexResult = preparedStatement.executeQuery();

            Map<String, Field> tmpMap = new HashMap<>();
            for (Field field : table.getFieldList()) {
                tmpMap.put(field.getFieldName(), field);
            }

            while (indexResult.next()) {
                Integer nonUnique = indexResult.getInt("non_unique");
                String keyName = indexResult.getString("key_name");
                String columnName = indexResult.getString("column_name");

                if (nonUnique == 1) {
                    continue;
                }

                List<Field> fields = table.getKeyIndexMap().get(keyName);
                if (fields == null) {
                    fields = new ArrayList<>();
                    table.getKeyIndexMap().put(keyName, fields);
                }

                fields.add(tmpMap.get(columnName));
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (indexResult != null) {
                try {
                    indexResult.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
