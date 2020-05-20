package com.example.aautotest2;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import java.io.IOException;

class TestUtilCtrl {
    public static void requestPermission(UiDevice device) {
        try {
            String result1 = device.executeShellCommand("pm grant com.cuiweiyou.uiautomator20 android.permission.WRITE_EXTERNAL_STORAGE");
            String result2 = device.executeShellCommand("pm grant com.cuiweiyou.uiautomator20 android.permission.READ_EXTERNAL_STORAGE");

            UtilLogs.saveLog("申请写sd卡shell反馈" + result1);
            UtilLogs.saveLog("申请读sd卡shell反馈" + result2);
        } catch (IOException e) {
            e.printStackTrace();
            UtilLogs.saveLog("申请权限异常" + e.getMessage());
        }
    }


    public static void lancherJYT(UiDevice device, String taskName) {
        try {
            String result = device.executeShellCommand("am start com.srba.siss/.ui.activity.SplashActivity");
            UtilLogs.saveLog("启动房通" + taskName + "反馈：" + result);

        } catch (IOException e) {
            e.printStackTrace();
            UtilLogs.saveLog("启动房通失败:" + e.getMessage());
        }
    }

    //重写findObject，等待元素出现再操作
    public static UiObject2 findObject(UiDevice device, String  resId) {
        device.wait(Until.hasObject(By.res(resId)), 5000);
        return device.findObject(By.res(resId));
    }

    public static void input_username(UiDevice device, String username) {
        UiObject2 username_edit = findObject(device, ManagerViewID.VIEW_ID_NAME_username_edit);
        username_edit.setText(username);
        UtilLogs.saveLog("输入用户名:" + username);
    }

    public static void input_password(UiDevice device, String password) {
        UiObject2 password_edit = findObject(device,ManagerViewID.VIEW_ID_NAME_password_edit);
        password_edit.setText(password);
        UtilLogs.saveLog("输入密码:" + password);
    }

    public static void click_login_btn(UiDevice device) {
        UiObject2 login_btn = findObject(device,ManagerViewID.VIEW_ID_NAME_login_btn);
        login_btn.click();
        UtilLogs.saveLog("点击登录按钮");
    }

}
