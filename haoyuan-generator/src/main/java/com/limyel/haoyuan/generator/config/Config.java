package com.limyel.haoyuan.generator.config;

import com.limyel.haoyuan.generator.util.PropertiesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class Config {

    public static final Boolean TABLE_IGNORE_PREFIX;
    public static final List<String> TABLE_EXCLUDE_LIST = new ArrayList<>();

    public static final String CONFIG_PROJECT;
    public static final String CONFIG_TARGET_PATH;

    static {
        TABLE_IGNORE_PREFIX = Boolean.valueOf(PropertiesUtil.getString("table.ignore.prefix"));
        TABLE_EXCLUDE_LIST.addAll(Arrays.asList(PropertiesUtil.getString("table.exclude.list").split(",")));

        CONFIG_PROJECT = PropertiesUtil.getString("config.project");
        CONFIG_TARGET_PATH = PropertiesUtil.getString("config.target.path");
    }

}
