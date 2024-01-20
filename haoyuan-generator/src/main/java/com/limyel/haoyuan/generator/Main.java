package com.limyel.haoyuan.generator;

import com.limyel.haoyuan.generator.bean.Table;
import com.limyel.haoyuan.generator.builder.EntityBuilder;
import com.limyel.haoyuan.generator.builder.TableBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Table> tables = TableBuilder.getTables();
        for (Table table : tables) {
            EntityBuilder.build(table);
        }
    }

}
