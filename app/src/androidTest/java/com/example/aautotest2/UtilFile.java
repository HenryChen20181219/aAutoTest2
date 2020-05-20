package com.example.aautotest2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class UtilFile {
    public static void appendText(String fileName, String context) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            writer.write(context);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void initSoucePath() {
        String dbPath = Constant.SOURCEPATH;
        File pathFile = new File(dbPath);
        if (!pathFile.exists()){
            pathFile.mkdirs();
            UtilLogs.saveLog("初始化资源路径");
        }
    }

    public static File getDeviceFile() {
        String devicePath = Constant.SOURCEPATH + "/device.text";
        UtilLogs.saveLog("手机适配文件" + devicePath);
        return new File(devicePath);
    }
}
