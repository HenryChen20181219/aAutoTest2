package com.example.aautotest2;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilLogs {
    //内部类引用外部对象，需声明为常量
    public static void saveLog(final String log) {
        saveLogs(new ArrayList<String>(){
            {
                add(log);
            }
        });
    }

    public static void saveLogs(List<String> log) {
        //如果log为空，则直接返回
        if (null == log || log.size() < 1) {
            return;
        }

        File file;
        //如果没有上次的日志文件路径，就新建日志路径，再新建日志文件
        if (null == Constant.lastLogFilePath || Constant.lastLogFilePath.length() < 1) {
            String logFile = createLogFile();//日志路径
            file = new File(logFile);
        } else {
            file = new File(Constant.lastLogFilePath);
            //有日志路径没有日志文件，在日志路径新建日志文件
            if (null == file || !file.exists()) {
                String logFile = createLogFile();
                file = new File(logFile);
            } else {
                long length = file.length();
                if (length > 1024 * 1024 * 1024 * 10) { //10MB
                    //文件超过10m大小时，新建一个日志路径，新建文件
                    Constant.lastLogFilePath = createLogFile();
                    file = new File(Constant.lastLogFilePath);
                }
            }
        }

        appendLine2File(file.getAbsolutePath(), log);
    }

    private static String createLogFile() {
        String path = Constant.SOURCEPATH + "/action_log";
        File file = new File(path);
        if (null == file || !file.exists()) {
            //创建日志文件夹
            file.mkdir();
        }
        //根据年月日创建日志路径
        path = path + "/log_" + Constant.DEVICE_SN + "_" + UtilDate.getNowYYMMDD() + ".log";
        return createFile(path);
    }

    private synchronized static String createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Constant.lastLogFilePath = path;
                appendLine2File(Constant.lastLogFilePath, new ArrayList<String>() {
                    {
                        add(UtilDate.getNowYYMMDDHHmmss() + "_创建了文件：" + Constant.lastLogFilePath);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    private static void appendLine2File(String filePath, List<String> strList) {
        if (null == strList || strList.size() < 1) {
            return;
        }
        // 获取当前日期时分秒
        String nowYYMMDDHHmmss = UtilDate.getNowYYMMDDHHmmss();
        String separator = System.getProperty("line.separator");

        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs(); //创建多层目录
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));
            for (int i = 0; i < strList.size();  i++) {
                String string = strList.get(i);
                Log.e("ard", string);

                bufferedWriter.write(nowYYMMDDHHmmss + "_" +string + separator);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedWriter) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
