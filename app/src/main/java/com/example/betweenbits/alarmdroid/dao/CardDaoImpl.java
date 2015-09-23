package com.example.betweenbits.alarmdroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.betweenbits.alarmdroid.db.DBHelper;
import com.example.betweenbits.alarmdroid.domain.Card;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunov0id on 27/07/15.
 */
public class CardDaoImpl implements CardDao {

    private DBHelper dbHelper;

    public CardDaoImpl(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public void insert(Card card) {
        try {
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("clock",card.getClock());
            values.put("title",card.getTitle());
            values.put("status", card.getStatus());

            long i = database.insert(dbHelper.TABLE_CARDS, null, values);
            database.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {
        try {
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("clock",card.getClock());
            values.put("title",card.getTitle());
            values.put("status", card.getStatus());

            database.update(dbHelper.TABLE_CARDS, values, dbHelper.COLUMN_ID + "= ?", new String[]{String.valueOf(card.getId())});
            database.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Card card) {
        try {
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            database.delete(dbHelper.TABLE_CARDS, dbHelper.COLUMN_ID + "= ?", new String[]{String.valueOf(card.getId())});
            database.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Card> getListOfCard() {
        List<Card> listOfCard = new ArrayList<>();
        String[] columns = new String[] {dbHelper.COLUMN_ID, dbHelper.COLUMN_TITLE, dbHelper.COLUMN_CLOCK, dbHelper.COLUMN_STATUS};
        try {
            SQLiteDatabase database = dbHelper.getReadableDatabase();

            Cursor cursor = database.query(dbHelper.TABLE_CARDS, columns, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Card clock = new Card();
                    clock.setId(cursor.getLong(0));
                    clock.setTitle(cursor.getString(1));
                    clock.setClock(cursor.getString(2));
                    clock.setStatus((cursor.getInt(3) == 1) ? true : false);

                    listOfCard.add(clock);
                }
            }
            cursor.close();
            database.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return listOfCard;
    }
}
