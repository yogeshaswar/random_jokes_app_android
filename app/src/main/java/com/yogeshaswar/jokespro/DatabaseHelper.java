package com.yogeshaswar.jokespro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String JOKE_TABLE = "JOKE_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_JOKE_SETUP = "COLUMN_JOKE_SETUP";
    private static final String COLUMN_JOKE_PUNCHLINE= "COLUMN_JOKE_PUNCHLINE";
    private static final String COLUMN_CATEGORY = "COLUMN_CATEGORY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "joke.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " +
                JOKE_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JOKE_SETUP + " TEXT, " +
                COLUMN_JOKE_PUNCHLINE + " TEXT, " +
                COLUMN_CATEGORY + " TEXT " + ")";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO ->
    }
    //insert
    public boolean addJoke(JokeModel jokeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, jokeModel.getId());
        cv.put(COLUMN_JOKE_SETUP, jokeModel.getSetup());
        cv.put(COLUMN_JOKE_PUNCHLINE, jokeModel.getPunchline());
        cv.put(COLUMN_CATEGORY, jokeModel.getCategory());

        long insert = db.insert(JOKE_TABLE, null, cv);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }


    }

    //get data
    public List<JokeModel> getAllJokes() {
        List<JokeModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + JOKE_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);

        //check
        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {

                JokeModel addJoke = new JokeModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                returnList.add(addJoke);

            }
        }


        return returnList;
    }

}
