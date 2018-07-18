package com.xuhailiang5794.ik.utils;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/18
 */
public class OutputUtils {
    private static final String lineSeparator;

    static {
        String ls = System.getProperty("line.separator");
        if (ls == null) {
            ls = "\n";
        }
        lineSeparator = ls;
    }

    public static void newLine(StringBuilder sb) {
        sb.append(lineSeparator);
    }

    public static String packageSeparator() {
        return ".";
    }
}
