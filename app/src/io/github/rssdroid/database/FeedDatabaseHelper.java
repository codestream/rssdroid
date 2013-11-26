package io.github.rssdroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedDatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "feeds.db";
    private static final int VERSION = 1;

    public FeedDatabaseHelper(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final String CREATE_TABLE_FEED_URLS = "CREATE TABLE feed_url ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FEED_URL_ID INTEGER NOT NULL, FEED_URL TEXT NOT NULL, FEED_DESCRIPTION TEXT NOT NULL, " +
            "UNIQUE(FEED_URL_ID) ON CONFLICT REPLACE ) ";
    private static final String CREATE_TABLE_FEEDS = "CREATE TABLE feeds ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FEED_ID INTEGER NOT NULL, FEED_CONTENT TEXT, UNIQUE(FEED_ID) ON CONFLICT REPLACE, " +
            "FOREIGN KEY (FEED_ID) REFERENCES feed_url(FEED_URL_ID))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FEED_URLS);
        db.execSQL(CREATE_TABLE_FEEDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS feed_url");
        db.execSQL("DROP TABLE IF EXISTS feeds");
    }
}
