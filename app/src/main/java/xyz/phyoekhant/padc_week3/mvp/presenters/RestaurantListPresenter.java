package xyz.phyoekhant.padc_week3.mvp.presenters;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import xyz.phyoekhant.padc_week3.MyApp;
import xyz.phyoekhant.padc_week3.adapters.RestaurantAdapter;
import xyz.phyoekhant.padc_week3.data.persistence.RestaurantsContract;
import xyz.phyoekhant.padc_week3.data.vos.RestaurantVO;
import xyz.phyoekhant.padc_week3.events.DataEvent;
import xyz.phyoekhant.padc_week3.models.RestaurantModel;
import xyz.phyoekhant.padc_week3.mvp.views.RestaurantListView;

/**
 * Created by Phyoe Khant on 7/4/17.
 */
public class RestaurantListPresenter extends BasePresenter {

    RestaurantListView restaurantListView;

    private RestaurantAdapter mRestaurantAdapter;

    List<RestaurantVO> mRestaurantList;

    public RestaurantListPresenter(RestaurantListView restaurantListView) {
        this.restaurantListView = restaurantListView;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DataEvent.RestaurantLoadedEvent event) {

        List<RestaurantVO> newRestaurantList = event.getRestaurantList();

        mRestaurantList = newRestaurantList;
        mRestaurantAdapter.setNewData(newRestaurantList);

        //to keep in Persistence Layer
        RestaurantVO.saveRestaurants(newRestaurantList);
    }

    public Loader<Cursor> onCreateLoader(Context context, int id, Bundle args) {
        return new CursorLoader(context,
                RestaurantsContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                RestaurantsContract.RestaurantEntry.COLUMN_SORT + " ASC"); // sort by COLUMN_SORT

    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<RestaurantVO> restaurantList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                RestaurantVO restaurant = RestaurantVO.parseFromCursor(data);
                restaurant.setTags(RestaurantVO.loadRestaurantTagsByTitle(restaurant.getTitle()));
                restaurantList.add(restaurant);
            } while (data.moveToNext());
        }

        Log.d(MyApp.TAG, "Retrieved restaurants : " + restaurantList.size());
        mRestaurantAdapter.setNewData(restaurantList);
        mRestaurantList = restaurantList;
    }

    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public RecyclerView getRestaurantAdapter(RecyclerView rvRestaurants, Context context){
        mRestaurantAdapter = new RestaurantAdapter(context);
        rvRestaurants.setAdapter(mRestaurantAdapter);
        return rvRestaurants;
    }

    public int getRestaurantListSize(){
        return mRestaurantList.size();
    }

    public void setRestaurantListFromModel(){
        mRestaurantList = RestaurantModel.getInstance().getRestaurantList();
    }
}
