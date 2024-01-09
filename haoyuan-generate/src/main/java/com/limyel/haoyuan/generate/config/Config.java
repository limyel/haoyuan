package com.limyel.haoyuan.generate.config;

import com.limyel.haoyuan.generate.util.PropertiesUtil;

public class Config {

    public static final Boolean IGNORE_TABLE_PREFIX;

    static {
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtil.getString("ignore.table.prefix"));
    }

}
