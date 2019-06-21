package jp.co.calace.lonor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "lonor.db";
    private final static int DATABASE_VERSION = 1;

    private final String MSG_TABLE = "messages";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        dropMsgTable(sqLiteDatabase);
        createMsgTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropMsgTable(sqLiteDatabase);
        createMsgTable(sqLiteDatabase);
    }


    private void createMsgTable(SQLiteDatabase sqLiteDatabase) {
        StringBuilder createMsgTableSQL = new StringBuilder();
        createMsgTableSQL.append("create table " + MSG_TABLE + " (");
        createMsgTableSQL.append("id int PRIMARY KEY AUTOINCREMENT,");
        createMsgTableSQL.append("sender_id int,");
        createMsgTableSQL.append("text char(50),");
        createMsgTableSQL.append("time datetime);");

        sqLiteDatabase.execSQL(createMsgTableSQL.toString());
    }

    private void dropMsgTable(SQLiteDatabase sqLiteDatabase) {
        final String sql = "drop table if exists " + MSG_TABLE + ";";
        sqLiteDatabase.execSQL(sql);
    }
}
