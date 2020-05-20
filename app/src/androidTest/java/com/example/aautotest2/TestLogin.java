package com.example.aautotest2;

import android.app.Instrumentation;
import android.content.Context;
import android.os.RemoteException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestLogin {

    private static Context context;
    private static UiDevice device;

    @BeforeClass
    public static void beforeClass() throws RemoteException {
        UtilLogs.saveLog("1.BeforeClass");

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        context = instrumentation.getTargetContext().getApplicationContext();

        device = UiDevice.getInstance(instrumentation);

        if (!device.isScreenOn()) {
            device.wakeUp();
        }

        TestUtilCtrl.requestPermission(device);
    }

    @Before
    public void before() {
        UtilLogs.saveLog("2.Before");
        UtilLogs.saveLog("");
        UtilLogs.saveLog("");
        UtilLogs.saveLog("");
        UtilLogs.saveLog("开始登录房通");
    }

    @After
    public void after()  {
        UtilLogs.saveLog("-1.After");
    }

    @AfterClass
    public static void afterClass() {
        UtilLogs.saveLog("-1.AfterClass");
    }


    @Test
    public void testLogin() {
        TestUtilCtrl.lancherJYT(device, "打开房通");
        TestUtilCtrl.input_username(device, "J130984");
        TestUtilCtrl.input_password(device, "123456");
        TestUtilCtrl.click_login_btn(device);
    }

}
