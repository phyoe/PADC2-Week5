package xyz.phyoekhant.padc_week3.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import xyz.phyoekhant.padc_week3.MyApp;

/**
 * Created by Phyoe Khant on 6/27/17.
 */
public class RestaurantsContract {

    public static final String CONTENT_AUTHORITY = MyApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RESTAURANTS = "restaurants";
    public static final String PATH_RESTAURANT_TAGS = "restaurant_tags";

    public static final class RestaurantEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String TABLE_NAME = "restaurants";

        //From API
        public static final String COLUMN_TITLE                 = "title";
        public static final String COLUMN_ADDR_SHORT            = "addr_short";
        public static final String COLUMN_IMAGE                 = "image";
        public static final String COLUMN_TOTAL_RATING_COUNT    = "total_rating_count";
        public static final String COLUMN_AVG_RATING_VALUE      = "average_rating_value";
        public static final String COLUMN_IS_AD                 = "is_ad";
        public static final String COLUMN_IS_NEW                = "is_new";
        public static final String COLUMN_LEAD_TIME_IN_MIN      = "lead_time_in_min";
        public static final String COLUMN_SORT                  = "sort";

        public static Uri buildRestaurantUri(long id) {
            //content://xyz.phyoekhant.padc_week3/restaurants/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantUriWithTitle(String restaurantTitle) {
            //content://xyz.phyoekhant.padc_week3/restaurants?title="The Coffee Bean & Tea Leaf"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, restaurantTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }

    }

    public static final class RestaurantTagEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANT_TAGS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_TAGS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_TAGS;

        public static final String TABLE_NAME = "restaurant_tags";

        public static final String COLUMN_RESTAURANT_TITLE = "restaurant_title";
        public static final String COLUMN_TAG = "tag";

        public static Uri buildRestaurantTagUri(long id) {
            //content://xyz.phyoekhant.padc_week3/restaurant_title/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantTagUriWithTitle(String restaurantTitle) {
            //content://xyz.phyoekhant.padc_week3/restaurant_title?restaurant_title="The Coffee Bean & Tea Leaf"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RESTAURANT_TITLE, restaurantTitle)
                    .build();
        }

        public static String getRestaurantTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RESTAURANT_TITLE);
        }
    }

}
