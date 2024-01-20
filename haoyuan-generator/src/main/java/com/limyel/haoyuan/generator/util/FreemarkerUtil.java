package com.limyel.haoyuan.generator.util;

import com.limyel.haoyuan.generator.bean.Table;
import com.limyel.haoyuan.generator.config.Config;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

public class FreemarkerUtil {

    private static Template template;

    private static Configuration fmConfig = new Configuration(Configuration.VERSION_2_3_29);

    static {
        fmConfig.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
    }

    public static void generate(Table table) {
        String path = Config.PACKAGE_ENTITY_PATH + Matcher.quoteReplacement(File.separator) + table.getBeanName()
                + table.getBeanNameSuffix() + ".java";
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
