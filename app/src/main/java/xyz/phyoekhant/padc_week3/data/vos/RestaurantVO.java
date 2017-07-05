package xyz.phyoekhant.padc_week3.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.phyoekhant.padc_week3.MyApp;
import xyz.phyoekhant.padc_week3.data.persistence.RestaurantsContract;

/**
 * Created by Phyoe Khant on 6/19/2017.
 */
public class RestaurantVO {

    @SerializedName("title")
    private String title;

    @SerializedName("addr-short")
    private String addrShort;

    @SerializedName("image")
    private String image;

    @SerializedName("total-rating-count")
    private int totalRatingCount;

    @SerializedName("average-rating-value")
    private double averageRatingValue;

    @SerializedName("is-ad")
    private boolean isAd;

    @SerializedName("is-new")
    private boolean isNew;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("lead-time-in-min")
    private int leadTimeInMin;

    private int sort;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddrShort(String addrShort) {
        this.addrShort = addrShort;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTotalRatingCount(int totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public void setAverageRatingValue(double averageRatingValue) {
        this.averageRatingValue = averageRatingValue;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setLeadTimeInMin(int leadTimeInMin) {
        this.leadTimeInMin = leadTimeInMin;
    }

    public String getTitle() {
        return title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public String getImage() {
        return image;
    }

    public int getTotalRatingCount() {
        return totalRatingCount;
    }

    public double getAverageRatingValue() {
        return averageRatingValue;
    }

    public boolean isAd() {
        return isAd;
    }

    public boolean isNew() {
        return isNew;
    }

    public String[] getTags() {
        return tags;
    }

    public int getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public int getSort() {
        return sort;
    }

    //For Persistence Layer
    public static void saveRestaurants(List<RestaurantVO> restaurantList) {
        Context context = MyApp.getContext();
        ContentValues[] restaurantCVs = new ContentValues[restaurantList.size()];
        for (int index = 0; index < restaurantList.size(); index++) {
            RestaurantVO restaurant = restaurantList.get(index);
            restaurantCVs[index] = restaurant.parseToContentValues();

            //Bulk insert into restaurant_tags.
            RestaurantVO.saveRestaurantTags(restaurant.getTitle(), restaurant.getTags());
        }

        //Bulk insert into restaurants.
        int insertedCount = context.getContentResolver().bulkInsert(RestaurantsContract.RestaurantEntry.CONTENT_URI, restaurantCVs);

        Log.d(MyApp.TAG, "Bulk inserted into restaurant table : " + insertedCount);
    }

    private static void saveRestaurantTags(String title, String[] tags) {
        ContentValues[] restaurantTagCVs = new ContentValues[tags.length];
        for (int index = 0; index < tags.length; index++) {
            String tag = tags[index];

            ContentValues cv = new ContentValues();
            cv.put(RestaurantsContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE, title);
            cv.put(RestaurantsContract.RestaurantTagEntry.COLUMN_TAG, tag);

            restaurantTagCVs[index] = cv;
        }

        Context context = MyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(RestaurantsContract.RestaurantTagEntry.CONTENT_URI, restaurantTagCVs);

        Log.d(MyApp.TAG, "Bulk inserted into restaurant_tags table : " + insertCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_TITLE, title);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_ADDR_SHORT, addrShort);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_IMAGE, image);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT, totalRatingCount);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_AVG_RATING_VALUE, averageRatingValue);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_IS_AD, isAd);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_IS_NEW, isNew);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN, leadTimeInMin);
        cv.put(RestaurantsContract.RestaurantEntry.COLUMN_SORT, sort);
        return cv;
    }

    public static RestaurantVO parseFromCursor(Cursor data) {
        RestaurantVO restaurant = new RestaurantVO();
        restaurant.title = data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_TITLE));
        restaurant.addrShort = data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_ADDR_SHORT));
        restaurant.image = data.getString(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IMAGE));
        restaurant.totalRatingCount = data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT));
        restaurant.averageRatingValue = data.getDouble(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_AVG_RATING_VALUE));
        restaurant.isAd = (data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IS_AD)) == 0) ? false : true;
        //Log.d(MyApp.TAG, "parseFromCursor: COLUMN_IS_AD" + data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IS_AD)));
        restaurant.isNew = (data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IS_NEW)) == 0) ? false : true;
        //Log.d(MyApp.TAG, "parseFromCursor: COLUMN_IS_NEW" + data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_IS_NEW)));
        restaurant.leadTimeInMin = data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN));
        restaurant.sort = data.getInt(data.getColumnIndex(RestaurantsContract.RestaurantEntry.COLUMN_SORT));
        return restaurant;
    }

    public static String[] loadRestaurantTagsByTitle(String title) {
        Context context = MyApp.getContext();
        ArrayList<String> tags = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RestaurantsContract.RestaurantTagEntry.buildRestaurantTagUriWithTitle(title),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                tags.add(cursor.getString(cursor.getColumnIndex(RestaurantsContract.RestaurantTagEntry.COLUMN_TAG)));
            } while (cursor.moveToNext());
        }

        String[] tagArray = new String[tags.size()];
        tags.toArray(tagArray);
        return tagArray;
    }
}
