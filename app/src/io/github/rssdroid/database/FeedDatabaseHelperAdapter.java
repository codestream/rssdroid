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
        mDatabase = this.feedDatabaseHelper.getReadableDatabase();
        mCursor = mDatabase.rawQuery("SELECT FEED_URL, FEED_DESCRIPTION FROM feed_url", null);
        return mCursor;
    }

    public void addFeed(String feedUrl, String feedDescription){
        mDatabase = this.feedDatabaseHelper.getReadableDatabase();
        String insertQuery = "INSERT INTO " + FEED_URL_TABLE + "(FEED_URL_ID, FEED_URL, FEED_DESCRIPTION) " +
                "VALUES ('" + 1 + "','" + feedUrl + "', '" + feedDescription + "')";
        mDatabase.execSQL(insertQuery);
    }
}
