package com.example.cloudkinetics.contactsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by cloud kinetics on 1/29/2017.
 */
public class MyDatabase extends SQLiteOpenHelper {

    private static final String MESSAGE_BODY = "message_body";
    private static final String SENT_TIMESTAMP = "sent_timestamp";
    private static final String RECEIVER_NAME = "receiver_name";
    private static final String RECEIVER_NUMBER = "receiver_number";
    private static final String TABLE_SENT_MESSAGES = "sent_messages";
    public static final String table_message = "CREATE TABLE " + TABLE_SENT_MESSAGES + "("
            + RECEIVER_NUMBER + " varchar(30),"
            + RECEIVER_NAME + " varchar(30),"
            + SENT_TIMESTAMP + " long,"
            + MESSAGE_BODY + " text)";

    public MyDatabase(Context context) {
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_message);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertMessage(Message m) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RECEIVER_NUMBER, m.getReceiverNumber());
        cv.put(RECEIVER_NAME, m.getReceiverName());
        cv.put(SENT_TIMESTAMP, m.getSentTimestamp());
        cv.put(MESSAGE_BODY, m.getMessageBody());
        db.insert(TABLE_SENT_MESSAGES, null, cv);
    }

    public ArrayList<Message> getMessageList() {
        ArrayList<Message> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from " + TABLE_SENT_MESSAGES;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                Message m = new Message();
                m.setReceiverNumber(c.getString(c.getColumnIndex(RECEIVER_NUMBER)));
                m.setReceiverName(c.getString(c.getColumnIndex(RECEIVER_NAME)));
                m.setSentTimestamp(c.getLong(c.getColumnIndex(SENT_TIMESTAMP)));
                m.setMessageBody(c.getString(c.getColumnIndex(MESSAGE_BODY)));
                list.add(m);
            }
            c.close();
        }
        db.close();
        return list;
    }
}
