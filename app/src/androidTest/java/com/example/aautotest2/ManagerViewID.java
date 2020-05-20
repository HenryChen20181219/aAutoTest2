package com.example.aautotest2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerViewID {
//    public static final String packageName = "com.srba.siss";
    public static final String VIEW_ID_NAME_username_edit = "com.srba.siss:id/et_username";
    public static final String VIEW_ID_NAME_password_edit = "com.srba.siss:id/et_password";
    public static final String VIEW_ID_NAME_login_btn = "com.srba.siss:id/btn_login";


    private Map<String, Map<String, String>>  deviceMap;
    private Map<String, String> selfViewIdKVMap;

    private ManagerViewID() {
        deviceMap = new HashMap<>();
    };

    private static ManagerViewID instance;

    public static ManagerViewID getInstance() {
        if (null == instance) {
            synchronized (ManagerViewID.class) {
                if (null == instance) {
                    instance = new ManagerViewID();
                }
            }
        }
        return instance;
    }

    public void initSelf(String deviceSN, String versionName) {
        init();
        String deviceAndWx = deviceSN.toLowerCase() + "==" +versionName;
        Map<String, String> viewIdKVMap = deviceMap.get(deviceAndWx);
        if (null == viewIdKVMap || viewIdKVMap.size() < 1) {
            return;
        }
        this.selfViewIdKVMap = viewIdKVMap;
    }

    private void init() {
        File deviceFile = UtilFile.getDeviceFile();
        if (!deviceFile.exists()) {
            UtilLogs.saveLog("");
            return;
        }
        try{
            InputStream is = new FileInputStream(deviceFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            List<String> group = new ArrayList<>();
            while (null != (line = reader.readLine())) {
                line = line.trim();
                if (line.length()<1) {
                    continue;
                }
                if (line.startsWith("###")) {
                    saveDevicesViewId(group);
                    group.clear();
                }
                group.add(line);
            }
            saveDevicesViewId(group);
            group.clear();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDevicesViewId(List<String> group) {
        if (group.size()<1) {
            return;
        }
        String title = group.get(0);
        String deviceSnAndWxVersion = title.replace("###", "").toLowerCase(); // ###开头的是设备分组。手机SN===微信版本
        Map<String, String> map = deviceMap.get(deviceSnAndWxVersion);
        if (null == map) {
            map = new HashMap<>();
            deviceMap.put(deviceSnAndWxVersion, map);
        }

        for (int i = 1; i< group.size(); i++) {
            String keyLine = group.get(i);
            String[] split = keyLine.split("==");
            String keyValue = split[0].trim();
            String[] split2 = keyValue.split("=");
            String view_id_key = split2[0];
            String view_id_value = split2[1];
            map.put(view_id_key, view_id_value);
        }
    }

    public String getSelfViewId(String viewidKey) {
        if (0 == 0) {
            return null; // todo 临时关闭
        }
        if (null == selfViewIdKVMap) {
            return null;
        }

        String viewid = selfViewIdKVMap.get(viewidKey);
        return viewid;
    }
}
