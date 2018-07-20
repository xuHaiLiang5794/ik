package com.xuhailiang5794.ik.utils;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/17
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String unEscape(String s) {
        s = s.replace("%7B", "{");
        s = s.replace("%3A", ":");
        s = s.replace("%5B", "[");
        s = s.replace("%7D", "}");
        s = s.replace("%2C", ",");
        s = s.replace("%5D", "]");
        return s;
    }
}
