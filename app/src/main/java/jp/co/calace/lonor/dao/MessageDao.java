package jp.co.calace.lonor.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import jp.co.calace.lonor.db.DBHelper;
import jp.co.calace.lonor.models.Character;
import jp.co.calace.lonor.models.Message;

public class MessageDao {

    public static final String TABLE_NAME = "messages";
    public static final String ID = "id";
    public static final String SENDER_ID = "sender_id";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    public static final DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SQLiteDatabase readableDb;
    private SQLiteDatabase writableDb;
    private Character self;
    private List<Character> friends;

    public MessageDao(DBHelper dbHelper, Character self, List<Character> friends) {
        this.readableDb = dbHelper.getReadableDatabase();
        this.writableDb = dbHelper.getWritableDatabase();
        this.self = self;
        this.friends = friends;
    }

    public List<Message> getAll() {
        List<Message> msgList = new ArrayList<>();
        String[] columns = {ID, SENDER_ID, TEXT, TIME};
        Cursor cursor = readableDb.query(TABLE_NAME, columns,null, null, null, null, TIME + " DESC," + ID + " DESC", "100");

        while (cursor.moveToNext()) {
            Message msg;
            try {
                msg = cursorToMsg(cursor);
                msgList.add(msg);
            } catch (Exception ignore) {};
        }

        Collections.reverse(msgList);
        return msgList;
    }

    public long insertMsg(Message message) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SENDER_ID, message.getSender().getId());
        contentValues.put(TEXT, message.getText());
        contentValues.put(TIME, iso8601Format.format(message.getTime()));
        long id = writableDb.insert(TABLE_NAME, null, contentValues);
        return id;
    }

    public Message cursorToMsg(Cursor cursor) throws Exception {
        int id = cursor.getInt(0);
        int senderId = cursor.getInt(1);
        String text = cursor.getString(2);
        Date time = iso8601Format.parse(cursor.getString(3));

        Message message = new Message();

        if (senderId < 0) {
            message.setSender(this.self);
            message.setType(Message.TYPE_SEND);
        } else if (senderId < this.friends.size()) {
            for (int i = 0; i < this.friends.size(); i++) {
                if (this.friends.get(i).getId() == senderId) {
                    message.setSender(this.friends.get(senderId));
                    message.setType(Message.TYPE_RECV);
                    break;
                }
            }
        }
        message.setText(text);
        message.setTime(time);

        return message;
    }

    public static DateFormat getIso8601Format() {
        return iso8601Format;
    }
}
