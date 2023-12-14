package com.example.ezcook.fcm;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ezcook.model.h_Notification_Model;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notification.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTIFICATION = "notification";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_TIME = "time";

    private static final String CREATE_TABLE_NOTIFICATION =
            "CREATE TABLE " + TABLE_NOTIFICATION + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_USER_ID + " TEXT," +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_CONTENT + " TEXT, " +
                    COLUMN_IMAGE + " INTEGER, " +
                    COLUMN_TIME + " LONG)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        onCreate(db);
    }

    public void addNotification(h_Notification_Model notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, notification.getUser_id());
        values.put(COLUMN_TITLE, notification.getTitle());
        values.put(COLUMN_CONTENT, notification.getContent());
        values.put(COLUMN_IMAGE, notification.getImage());
        values.put(COLUMN_TIME, notification.getTime());
        // Inserting Row
        db.insert(TABLE_NOTIFICATION, null, values);
        db.close(); // Đóng kết nối cơ sở dữ liệu
    }


    @SuppressLint("Range")
    public List<h_Notification_Model> getAllNotifications(String userId) {
        List<h_Notification_Model> notificationList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTIFICATION +
                " WHERE " + COLUMN_USER_ID + " = '" + userId + "'" +
                " ORDER BY " + COLUMN_TIME + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                h_Notification_Model notification = new h_Notification_Model(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)),
                        cursor.getLong(cursor.getColumnIndex(COLUMN_TIME))
                );
                notificationList.add(notification);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return notificationList;
    }


}
