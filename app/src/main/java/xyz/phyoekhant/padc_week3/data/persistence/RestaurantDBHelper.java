package xyz.phyoekhant.padc_week3.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import xyz.phyoekhant.padc_week3.data.persistence.RestaurantsContract.RestaurantEntry;
import xyz.phyoekhant.padc_week3.data.persistence.RestaurantsContract.RestaurantTagEntry;

/**
 * Created by Phyoe Khant on 6/27/17.
 */
public class RestaurantDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "restaurants.db";

    private static final String SQL_CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
            RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RestaurantEntry.COLUMN_ADDR_SHORT + " TEXT NULL, " +
            RestaurantEntry.COLUMN_IMAGE + " TEXT NULL, " +
            RestaurantEntry.COLUMN_TOTAL_RATING_COUNT + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_AVG_RATING_VALUE + " REAL NOT NULL, " +
            RestaurantEntry.COLUMN_IS_AD + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_IS_NEW + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN + " INTEGER NOT NULL, " +
            RestaurantEntry.COLUMN_SORT + " INTEGER NULL, " +

            " UNIQUE (" + RestaurantEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_RESTAURANT_TAG_TABLE = "CREATE TABLE " + RestaurantTagEntry.TABLE_NAME + " (" +
            RestaurantTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " TEXT NOT NULL, " +
            RestaurantTagEntry.COLUMN_TAG + " TEXT NOT NULL, " +

            " UNIQUE (" + RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + ", " +
            RestaurantTagEntry.COLUMN_TAG + ") ON CONFLICT IGNORE" +
            " );";


    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RESTAURANT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RESTAURANT_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RestaurantTagEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
