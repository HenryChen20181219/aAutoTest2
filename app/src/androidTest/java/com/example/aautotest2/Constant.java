package com.example.aautotest2;

import android.os.Environment;

import java.io.File;

public class Constant {
    public static final String SOURCEPATH = Environment.getDataDirectory().getAbsolutePath() + "/data/uiautomator20"; //存储在手机的目录
    public static String DEVICE_SN;
    public static String lastLogFilePath;
}
