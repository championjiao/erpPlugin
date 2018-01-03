package com.qw.util;

/**
 * Created by Administrator on 2017/2/17.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils  {
    public StringUtils() {
    }

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || String.valueOf(obj).trim().length() == 0;
    }

    public static String trim(Object obj) {
        return obj == null?null:trim(String.valueOf(obj));
    }
}
