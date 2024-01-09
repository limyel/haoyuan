package com.limyel.haoyuan.generate.builder;

import com.limyel.haoyuan.generate.bean.Table;
import com.limyel.haoyuan.generate.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class TableBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TableBuilder.class);

    private static Connection connection = null;

    private static final String SQL_SHOW_TABLE_STATUS = "SHOW TABLE STATUS";

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

    public static void getTables() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SHOW_TABLE_STATUS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tableName = resultSet.getString("name");
                String comment = resultSet.getString("comment");
                logger.info("tableName: {}, comment: {}.", tableName, comment);

                Table table = new Table();

            }
        } catch (Exception e) {

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
    }

}
