package com.example.aautotest2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate {
    static DateFormat dfYYMMDDHHmmss = new SimpleDateFormat("yy-MM-dd-HH:mm:ss");
    static DateFormat dfYYMMDD = new SimpleDateFormat("yy-MM-dd");

    private UtilDate() {
    }

    public static String getNowYYMMDD() {
        String yyMMdd = dfYYMMDD.format(new Date());
        return yyMMdd;
    }

    public static String getNowYYMMDD(long timestamp) {
        String yyMMdd = dfYYMMDD.format(new Date(new Long(timestamp)));
        return yyMMdd;
    }

    public static String getNowYYMMDDHHmmss() {
        String yyMMddhhmmss = dfYYMMDDHHmmss.format(new Date());
        return yyMMddhhmmss;
    }

    public static String getNowYYMMDDHHmmss(long timestamp) {
        String yyMMddhhmmss = dfYYMMDDHHmmss.format(new Date(new Long(timestamp)));
        return yyMMddhhmmss;
    }
}