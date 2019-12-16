package com.smarthaier.common.utils;

public class StringUtils {
    public static boolean isNotNull(Object obj) {
        if (obj == null)
            return false;
        else
            return true;
    }

    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String trim(String str) {
        return (str == null ? "" : str.trim());

    }

    public static boolean isEmpty(String strPattern) {
        if (strPattern == null || strPattern.equals(""))
            return true;
        return false;
    }

    public static boolean isEmpty(Object[] argArray) {
        if (argArray == null || argArray.length==0)
            return true;
        return false;
    }

    public static boolean isNull(Object object) {
        if (object == null )
            return true;
        return false;
    }

    public static boolean isNotEmpty(String url) {
        if (url == null || "".equals(url))
            return false;
        return true;
    }
}
