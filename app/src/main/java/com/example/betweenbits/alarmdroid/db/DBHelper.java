package com.example.betweenbits.alarmdroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by brunov0id on 27/07/15.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "card.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CARDS = "cards";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLOCK = "clock";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_STATUS = "status";

    private String query() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS " + TABLE_CARDS + " (");
        sql.append(""  + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT");
        sql.append(", " + COLUMN_CLOCK + " TEXT NOT NULL");
        sql.append(", " + COLUMN_TITLE + " TEXT NOT NULL");
        sql.append(", " + COLUMN_STATUS + " INTEGER NOT NULL);");
        return sql.toString();
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        onCreate(db);
    }
}
