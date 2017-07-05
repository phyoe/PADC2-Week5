package xyz.phyoekhant.padc_week3.fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.phyoekhant.padc_week3.MyApp;
import xyz.phyoekhant.padc_week3.R;
import xyz.phyoekhant.padc_week3.adapters.RestaurantAdapter;
import xyz.phyoekhant.padc_week3.data.vos.RestaurantVO;
import xyz.phyoekhant.padc_week3.events.DataEvent;
import xyz.phyoekhant.padc_week3.models.RestaurantModel;
import xyz.phyoekhant.padc_week3.utils.RestaurantsConstants;
import xyz.phyoekhant.padc_week3.data.persistence.RestaurantsContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.tv_total_restaurant_count)
    TextView tvTotalRestaurantCount;

    @BindView(R.id.rv_restaurants)
    RecyclerView rvRestaurants;

    private RestaurantAdapter mRestaurantAdapter;

    public static RestaurantListFragment newInstance() {
        RestaurantListFragment fragment = new RestaurantListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        ButterKnife.bind(this, rootView);

        List<RestaurantVO> restaurantList = RestaurantModel.getInstance().getRestaurantList();
        tvTotalRestaurantCount.setText(restaurantList.size() + " restaurants deliver to you");

        mRestaurantAdapter = new RestaurantAdapter(getContext());
        rvRestaurants.setAdapter(mRestaurantAdapter);

        int gridColumnSpanCount = 1;
        rvRestaurants.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DataEvent.RestaurantLoadedEvent event) {

        List<RestaurantVO> newRestaurantList = event.getRestaurantList();
        tvTotalRestaurantCount.setText(newRestaurantList.size() + " restaurants deliver to you");
        mRestaurantAdapter.setNewData(newRestaurantList);

        //to keep in Persistence Layer
        RestaurantVO.saveRestaurants(newRestaurantList);
    }

    /**
     * For Persistence Layer
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(RestaurantsConstants.RESTAURANT_LIST_LOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                RestaurantsContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                RestaurantsContract.RestaurantEntry.COLUMN_SORT + " ASC"); // sort by COLUMN_SORT
    }

    @Override
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
        tvTotalRestaurantCount.setText(restaurantList.size() + " restaurants deliver to you");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
