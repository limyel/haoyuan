package com.limyel.haoyuan.generator.util;

import com.limyel.haoyuan.generator.bean.Table;
import com.limyel.haoyuan.generator.config.Config;
import com.limyel.haoyuan.generator.config.Type;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;
import java.util.regex.Matcher;

public class FreemarkerUtil {

    private static Template template;

    private static Configuration fmConfig = new Configuration(Configuration.VERSION_2_3_29);

    static {
        try {
            String templatePath = System.getProperty("user.dir") + "/haoyuan-generator/src/main/resources/templates/";
            fmConfig.setDirectoryForTemplateLoading(new File(templatePath));
            fmConfig.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
            fmConfig.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void generate(Table table, Type type) {
        try {
            template = fmConfig.getTemplate(type.getPath());

            String savePath = Config.CONFIG_TARGET_PATH + Matcher.quoteReplacement(File.separator) + Config.CONFIG_PROJECT +
                    Matcher.quoteReplacement(File.separator) + table.getModuleName() + table.getPackageName();

            File dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileOutputStream outputStream = new FileOutputStream(savePath + Matcher.quoteReplacement(File.separator) + String.format(type.getClassName(),
                    table.getBeanName()));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
