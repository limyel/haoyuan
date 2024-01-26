package com.limyel.haoyuan.generator;

import com.limyel.haoyuan.generator.bean.Table;
import com.limyel.haoyuan.generator.builder.EntityBuilder;
import com.limyel.haoyuan.generator.builder.TableBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir") + "/haoyuan-generator/src/main/resources/templates");

        List<Table> tables = TableBuilder.getTables();
        for (Table table : tables) {
            EntityBuilder.build(table);
        }
    }

}
