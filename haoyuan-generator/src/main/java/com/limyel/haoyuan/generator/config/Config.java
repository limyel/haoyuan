package com.limyel.haoyuan.generator.config;

import com.limyel.haoyuan.generator.util.PropertiesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    public static final Boolean IGNORE_TABLE_PREFIX;
    public static final List<String> EXCLUDE_TABLE_LIST = new ArrayList<>();

    public static final String SUFFIX_BEAN_PARAM;

    static {
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtil.getString("ignore.table.prefix"));

        EXCLUDE_TABLE_LIST.addAll(Arrays.asList(PropertiesUtil.getString("exclude.table.list").split(",")));

        SUFFIX_BEAN_PARAM = PropertiesUtil.getString("suffix.bean.param");
    }

}
