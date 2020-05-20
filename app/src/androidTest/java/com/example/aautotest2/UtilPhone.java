package com.example.aautotest2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class UtilPhone {
    private UtilPhone() {
    }

    public static String getDeviceSN() {
        String serialNumber = android.os.Build.SERIAL;

        UtilLogs.saveLog("手机SN：" + serialNumber);

        return serialNumber;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.srba.siss", 0);

            UtilLogs.saveLog("房通版本：" + packageInfo.versionName);

            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.srba.siss", 0);

            UtilLogs.saveLog("房通版本：" + packageInfo.versionCode);

            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
