package com.limyel.haoyuan;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastAutoGeneratorTest {

    public static void before() throws SQLException {
        Connection conn = DATA_SOURCE_CONFIG.build().getConn();
        InputStream inputStream = FastAutoGeneratorTest.class.getResourceAsStream("/sql/init.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/haoyuan?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "123456");

    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
//        before();
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig(builder -> builder.author("limyel").outputDir("/home/limyel/Workspace/haoyuan/haoyuan-backend/haoyuan-model/src/main/java/com/limyel/haoyuan"))
                // 包配置
                .packageConfig(builder -> builder.parent("com.limyel.haoyuan"))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle().entityBuilder()
                        .enableLombok().build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
