package com.example.aautotest2;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

public class RootApplication extends Application {
    private static Context applicationContext;
    private static ClipboardManager clipboardManager;

    public void onCreate() {
        super.onCreate();
        Log.e("arg", "管微Client");//error打印红色
        applicationContext = getApplicationContext();
        clipboardManager = (ClipboardManager) applicationContext.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public static Context getAppContext() {
        Log.e("","");
        return applicationContext;
    }

    public static ClipboardManager getClipboardManager() {
        return clipboardManager;
    }
}
