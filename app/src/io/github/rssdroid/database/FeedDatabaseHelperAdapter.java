package io.github.rssdroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FeedDatabaseHelperAdapter {

    private static final String FEED_TABLE = "feeds";
    private static final String FEED_URL_TABLE = "feed_url";
    private Cursor mCursor;
    private SQLiteDatabase mDatabase;

    private FeedDatabaseHelper feedDatabaseHelper;

    public FeedDatabaseHelperAdapter(Context context){
        this.feedDatabaseHelper = new FeedDatabaseHelper(context, FeedDatabaseHelper.DATABASE_NAME,
                FeedDatabaseHelper.VERSION);
    }

    public Cursor findFeedUrls(){
        mDatabase = feedDatabaseHelper.getReadableDatabase();
        mCursor = mDatabase.rawQuery("SELECT FEED_URL TEXT, FEED_DESCRIPTION FROM feed_url", null);
        return mCursor;
    }

    public void addFeed(String feedUrl, String feedDescription){
        mDatabase.execSQL("INSERT INTO " + FEED_URL_TABLE + "(FEED_URL TEXT, FEED_DESCRIPTION) VALUES (" + feedUrl + "," + feedDescription +")");
    }
}
