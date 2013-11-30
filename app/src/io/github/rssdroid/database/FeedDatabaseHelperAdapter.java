package io.github.rssdroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
        String query = "SELECT * FROM feed_url";
        mCursor = mDatabase.rawQuery(query, null);
        return mCursor;
    }

    public void addFeed(String feedUrl, String feedDescription){
        mDatabase = feedDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FEED_URL", feedUrl);
        contentValues.put("FEED_DESCRIPTION", feedDescription);
        mDatabase.insert(FEED_URL_TABLE, null, contentValues);
        if(mDatabase.isOpen()){
            mDatabase.close();
        }
        /*mDatabase = this.feedDatabaseHelper.getWritableDatabase();
        String insertQuery = "INSERT INTO " + FEED_URL_TABLE + "(FEED_URL, FEED_DESCRIPTION) " +
                "VALUES ('" + feedUrl + "', '" + feedDescription + "')";
        mDatabase.execSQL(insertQuery);*/
    }
}
