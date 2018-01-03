package com.qw.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @className: DateFormatUtil
 * @description:
 * @author: zhumeiqing
 * @create: 2017/3/23
 **/
public class DateFormatUtil {
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String HHMMSS = "HH:mm:ss";
    public static final String YYYYMMDDHHMMSS_COMPACT = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDDHH_DOT = "yyyy.MM.dd";


    public static String format(Date date){
        return format(date,YYYYMMDD);
    }

    public static String format(Date date,String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 日期格式化 HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return format(date, HHMMSS);
    }

    /**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return format(date, YYYYMMDDHHMMSS);
    }


    /**
     * 获取前一天
     * @return
     */
    public static String getPreDay(){
        Date beginDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
        String dayBefore = format(c.getTime());
        return dayBefore;
    }


    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

}
