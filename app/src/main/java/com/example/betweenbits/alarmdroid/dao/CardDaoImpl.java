package com.example.betweenbits.alarmdroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        ContentValues values = new ContentValues();

    }

    @Override
    public void updateCard(Card card) {

    }

    @Override
    public void deleteCard(Card card) {

    }
}
