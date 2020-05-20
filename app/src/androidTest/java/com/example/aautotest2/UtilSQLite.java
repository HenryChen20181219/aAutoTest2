package com.example.aautotest2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

class UtilSQLite extends SQLiteOpenHelper {

    private final String NAME_TB_CHAT = "tb_chat";
    private final String NAME_TB_SEARCH = "tb_search";
    private final String NAME_TB_FRIEND = "tb_friend";

    private static UtilSQLite instance;
    private static String txtSearchFilePath;
    private static String txtChatFilePath;
    private static String txtListFilePath;

    public UtilSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static UtilSQLite getInstance(Context context) {
        if (null == instance) {
            synchronized (UtilSQLite.class){
                if (null == instance) {
                    UtilLogs.saveLog("SQLITE_创建工具类");

                    File pathFile = new File(Constant.SOURCEPATH);
                    String dbFilePath = pathFile.getAbsolutePath() + "/friend.db";
                    txtSearchFilePath = pathFile.getAbsolutePath() + "/friendSearch.txt";
                    txtChatFilePath = pathFile.getAbsolutePath() + "/friendChat.txt";
                    txtListFilePath = pathFile.getAbsolutePath() + "/friendList.txt";

                    UtilLogs.saveLog("自定义db路径:" + dbFilePath);

                    File dbFile = new File(dbFilePath);
                    if (!dbFile.exists()) {
                        try {
                            dbFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    File txtSearchFile = new File(txtSearchFilePath);
                    if (!txtSearchFile.exists()) {
                        try {
                            txtSearchFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    File txtChatFilePath = new File(txtSearchFilePath);
                    if (!txtChatFilePath.exists()) {
                        try {
                            txtChatFilePath.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    File txtListFilePath = new File(txtSearchFilePath);
                    if (!txtListFilePath.exists()) {
                        try {
                            txtListFilePath.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    instance = new UtilSQLite(context, dbFilePath, null, 1);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UtilLogs.saveLog("SQLITE_ 初始化数据库：" + db.getPath());

        // 如果表数据将来用于SimpleCursorAdapter，注意其只识别带下划线的主键。 _id 主键
        db.execSQL("CREATE TABLE " + NAME_TB_SEARCH + " (" + //
                "_id INTEGER PRIMARY KEY, " + //
                "phone VARCHAR, " +    // 查找的电话号码
                "wxid VARCHAR, " +     // 微信号
                "notename VARCHAR, " + // 备注名
                "nickname VARCHAR, " + // 昵称
                "timestamp VARCHAR, " + // 查找时间戳
                "datetime VARCHAR, " +  // 查找时间年月日
                "note VARCHAR " +      // 查找结果：yes-请求了加好友/none-未找到
                ");");

        db.execSQL("CREATE TABLE " + NAME_TB_FRIEND + " (" + //
                "_id INTEGER PRIMARY KEY, " +   //
                "phone VARCHAR, " +             // 电话
                "wxid VARCHAR, " +              // 微信号
                "notename VARCHAR, " +          // 备注名
                "nickname VARCHAR, " +          // 昵称
                "timestamp VARCHAR, " +         // 互为好友时间戳
                "datetime VARCHAR, " +          // 互为好友时间年月日
                "type VARCHAR, " +              // 互为好友方式：reactive对方请求的，proactive我主动查找申请好友的
                "isothersidedeleted INTEGER " + // 对方是否已经删除了好友关系1-删了，0-没
                ");");

        db.execSQL("CREATE TABLE " + NAME_TB_CHAT + " (" + //
                "_id INTEGER PRIMARY KEY, " + //
                "firend VARCHAR, " +      // 好友的电话
                "wxid VARCHAR, " +        // 好友的微信号
                "message VARCHAR, " +     // 对方聊天内容
                "timestamp VARCHAR, " +   // 聊天时间戳
                "datetime VARCHAR, " +    // 聊天时间年月日
                "role VARCHAR " +         // me-我、other-对方
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
