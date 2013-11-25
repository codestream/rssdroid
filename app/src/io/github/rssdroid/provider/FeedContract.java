package io.github.rssdroid.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import static android.content.ContentResolver.CURSOR_DIR_BASE_TYPE;
import static android.content.ContentResolver.CURSOR_ITEM_BASE_TYPE;

public class FeedContract {
    public static final String CONTENT_AUTHORITY = "io.github.rssdroid.provider";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static class FeedUrls implements BaseColumns, FeedUrlColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("feed_urls").build();
        public static final String CONTENT_TYPE = CURSOR_DIR_BASE_TYPE + "/vnd.rssdroid.feedUrls";
        public static final String CONTENT_ITEM_TYPE = CURSOR_ITEM_BASE_TYPE + "/vnd.rssdroid.feedUrl";

        public static Uri buildItemUri(String feedUrlId) {
            return CONTENT_URI.buildUpon().appendPath(feedUrlId).build();
        }

        public static String getFeedUrlId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public interface Query {
            String[] PROJECTION = {
                FeedUrls._ID,
                FeedUrls.FEED_URL_ID,
                FeedUrls.FEED_DESCRIPTION_ID,
                FeedUrls.FEED_URL
            };

            int _ID = 0;
            int FEED_URL_ID = 1;
            int FEED_DESCRIPTION_ID = 2;
            int FEED_URL = 3;
        }
    }

    public static class FeedDescription implements BaseColumns, FeedDescriptionColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("feed_descriptions").build();
        public static final String CONTENT_TYPE = CURSOR_DIR_BASE_TYPE + "/vnd.rssdroid.feedDescriptions";
        public static final String CONTENT_ITEM_TYPE = CURSOR_ITEM_BASE_TYPE + "/vnd.rssdroid.feedDescription";

        public static Uri buildItemUri(String feedDescriptionId) {
            return CONTENT_URI.buildUpon().appendPath(feedDescriptionId).build();
        }

        public static String getFeedDescriptionId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public interface Query {
            String[] PROJECTION = {
                FeedDescription._ID,
                FeedDescription.FEED_DESCRIPTION_ID,
                FeedDescription.FEED_DESCRIPTION
            };

            int _ID = 0;
            int FEED_DESCRIPTION_ID = 1;
            int FEED_DESCRIPTION = 2;
        }
    }

    public static class Feed implements BaseColumns, FeedColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("feeds").build();
        public static final String CONTENT_TYPE = CURSOR_DIR_BASE_TYPE + "/vnd.rssdroid.feeds";
        public static final String CONTENT_ITEM_TYPE = CURSOR_ITEM_BASE_TYPE + "/vnd.rssdroid.feed";

        public static Uri buildItemUri(String feedId) {
            return CONTENT_URI.buildUpon().appendPath(feedId).build();
        }

        public static String getFeedId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public interface Query {
            String[] PROJECTION = {
                    Feed._ID,
                    Feed.FEED_URL_ID,
                    Feed.FEED_ID,
                    Feed.FEED_CONTENT
            };

            int _ID = 0;
            int FEED_URL_ID = 1;
            int FEED_ID = 2;
            int FEED_CONTENT = 3;
        }
    }

    interface FeedDescriptionColumns {
        String FEED_DESCRIPTION_ID = "feed_description_id";
        String FEED_DESCRIPTION = "feed_description";
    }

    interface FeedUrlColumns {
        String FEED_URL_ID = "feed_url_id";
        String FEED_DESCRIPTION_ID = "feed_description_id";
        String FEED_URL = "feed_url";
    }

    interface FeedColumns {
        String FEED_URL_ID = "feed_url_id";
        String FEED_ID = "feed_id";
        String FEED_CONTENT = "feed_content";
    }
}
