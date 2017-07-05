package xyz.phyoekhant.padc_week3.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by Phyoe Khant on 6/27/17.
 */
public class RestaurantProvider extends ContentProvider {

    public static final int RESTAURANT = 100;
    public static final int RESTAURANT_TAG = 200;

    private static final String sRestaurantTitleSelection = RestaurantsContract.RestaurantEntry.COLUMN_TITLE + " = ?";
    private static final String sRestaurantTagSelectionWithTitle = RestaurantsContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE + " = ?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private RestaurantDBHelper mRestaurantDBHelper;

    @Override
    public boolean onCreate() {
        mRestaurantDBHelper = new RestaurantDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;

        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANT:
                String attractionTitle = RestaurantsContract.RestaurantEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(attractionTitle)) {
                    selection = sRestaurantTitleSelection;
                    selectionArgs = new String[]{attractionTitle};
                }
                queryCursor = mRestaurantDBHelper.getReadableDatabase().query(RestaurantsContract.RestaurantEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            case RESTAURANT_TAG:
                String title = RestaurantsContract.RestaurantTagEntry.getRestaurantTitleFromParam(uri);
                if (title != null) {
                    selection = sRestaurantTagSelectionWithTitle;
                    selectionArgs = new String[]{title};
                }
                queryCursor = mRestaurantDBHelper.getReadableDatabase().query(RestaurantsContract.RestaurantTagEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RESTAURANT:
                return RestaurantsContract.RestaurantEntry.DIR_TYPE;
            case RESTAURANT_TAG:
                return RestaurantsContract.RestaurantTagEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;

        switch (matchUri) {
            case RESTAURANT: {
                long _id = db.insert(RestaurantsContract.RestaurantEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = RestaurantsContract.RestaurantEntry.buildRestaurantUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case RESTAURANT_TAG: {
                long _id = db.insert(RestaurantsContract.RestaurantTagEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = RestaurantsContract.RestaurantTagEntry.buildRestaurantTagUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mRestaurantDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(RestaurantsContract.CONTENT_AUTHORITY, RestaurantsContract.PATH_RESTAURANTS, RESTAURANT);
        uriMatcher.addURI(RestaurantsContract.CONTENT_AUTHORITY, RestaurantsContract.PATH_RESTAURANT_TAGS, RESTAURANT_TAG);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case RESTAURANT:
                return RestaurantsContract.RestaurantEntry.TABLE_NAME;
            case RESTAURANT_TAG:
                return RestaurantsContract.RestaurantTagEntry.TABLE_NAME;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}
