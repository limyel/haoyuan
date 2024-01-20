package com.limyel.haoyuan.generator.config;

import com.limyel.haoyuan.generator.util.PropertiesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class Config {

    public static final Boolean IGNORE_TABLE_PREFIX;
    public static final List<String> EXCLUDE_TABLE_LIST = new ArrayList<>();

    public static final String SUFFIX_BEAN_PARAM;

    public static final String PATH_TARGET;

    public static final String PACKAGE_BASE;
    public static final String PACKAGE_BASE_PATH;
    public static final String PACKAGE_ENTITY;
    public static final String PACKAGE_ENTITY_PATH;
    public static final String PACKAGE_DTO;
    public static final String PACKAGE_DTO_PATH;
    public static final String PACKAGE_VO;
    public static final String PACKAGE_VO_PATH;

    static {
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtil.getString("ignore.table.prefix"));

        EXCLUDE_TABLE_LIST.addAll(Arrays.asList(PropertiesUtil.getString("exclude.table.list").split(",")));

        SUFFIX_BEAN_PARAM = PropertiesUtil.getString("suffix.bean.param");

        PATH_TARGET = PropertiesUtil.getString("path.target");

        PACKAGE_BASE = PropertiesUtil.getString("package.base");
        PACKAGE_BASE_PATH = PATH_TARGET + Matcher.quoteReplacement(File.separator) + PACKAGE_BASE.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        PACKAGE_ENTITY = PropertiesUtil.getString("package.entity");
        PACKAGE_ENTITY_PATH = PACKAGE_BASE_PATH + Matcher.quoteReplacement(File.separator) + PACKAGE_ENTITY.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        PACKAGE_DTO = PropertiesUtil.getString("package.dto");
        PACKAGE_DTO_PATH = PACKAGE_BASE_PATH + Matcher.quoteReplacement(File.separator) + PACKAGE_DTO.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        PACKAGE_VO = PropertiesUtil.getString("package.vo");
        PACKAGE_VO_PATH = PACKAGE_BASE_PATH + Matcher.quoteReplacement(File.separator) + PACKAGE_DTO.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
    }

}
