package com.example.betweenbits.alarmdroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.betweenbits.alarmdroid.db.DBHelper;
import com.example.betweenbits.alarmdroid.domain.Card;

import java.sql.SQLException;

/**
 * Created by brunov0id on 27/07/15.
 */
public class CardDaoImpl implements CardDao{

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public CardDaoImpl(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void insertCard(Card card) {
        try {
            ContentValues values = new ContentValues();
            values.put("clock",card.getClock());
            values.put("title",card.getTitle());
            values.put("status",card.getStatus());

            this.database.insert(dbHelper.TABLE_CARDS, null, values);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCard(Card card) {
        try {
            ContentValues values = new ContentValues();
            values.put("clock",card.getClock());
            values.put("title",card.getTitle());
            values.put("status",card.getStatus());

            this.database.update(dbHelper.TABLE_CARDS, values, "id = " + card.getId(), null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCard(Card card) {
        try {
            this.database.delete(dbHelper.TABLE_CARDS, "id = ?", new String[]{String.valueOf(card.getId())});
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
}
