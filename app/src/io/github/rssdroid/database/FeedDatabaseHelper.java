package io.github.rssdroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Collections;

public class FeedDatabaseHelper extends SQLiteOpenHelper{
    protected static final String DATABASE_NAME = "feeds.db";
    protected static final int VERSION = 1;

    public FeedDatabaseHelper(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final String CREATE_TABLE_FEED_URLS = "CREATE TABLE feed_url ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FEED_URL TEXT NOT NULL, FEED_DESCRIPTION TEXT NOT NULL)";
    private static final String CREATE_TABLE_FEEDS = "CREATE TABLE feeds ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FEED_CONTENT TEXT, FEED_ID TEXT, " +
            "FOREIGN KEY (FEED_ID) REFERENCES feed_url(FEED_URL))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FEED_URLS);
        db.execSQL(CREATE_TABLE_FEEDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS feed_url");
        db.execSQL("DROP TABLE IF EXISTS feeds");
        onCreate(db);
    }
}
