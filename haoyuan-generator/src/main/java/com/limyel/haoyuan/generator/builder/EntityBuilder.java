package com.limyel.haoyuan.generator.builder;

import com.limyel.haoyuan.generator.bean.Table;
import com.limyel.haoyuan.generator.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class EntityBuilder {

    private static final Logger logger = LoggerFactory.getLogger(EntityBuilder.class);

    public static void build(Table table) {
        File folder = new File(Config.PACKAGE_ENTITY_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, table.getBeanName() + table.getBeanNameSuffix() + ".java");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
