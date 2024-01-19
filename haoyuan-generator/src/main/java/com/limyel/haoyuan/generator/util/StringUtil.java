package com.limyel.haoyuan.generator.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public static String underlineToCamel(String s, Boolean firstUpper) {
        StringBuilder builder = new StringBuilder();
        String[] strings = s.split("_");

        int index = 0;
        if (!firstUpper) {
            builder.append(strings[index++]);
        }
        for (; index < strings.length; index++) {
            builder.append(firstLetterUpper(strings[index]));
        }

        return builder.toString();
    }

    public static String firstLetterUpper(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
