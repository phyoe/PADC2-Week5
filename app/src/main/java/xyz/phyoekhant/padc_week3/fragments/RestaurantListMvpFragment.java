package xyz.phyoekhant.padc_week3.fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.phyoekhant.padc_week3.R;
import xyz.phyoekhant.padc_week3.events.DataEvent;
import xyz.phyoekhant.padc_week3.mvp.presenters.RestaurantListPresenter;
import xyz.phyoekhant.padc_week3.mvp.views.RestaurantListView;
import xyz.phyoekhant.padc_week3.utils.RestaurantsConstants;

public class RestaurantListMvpFragment extends BaseFragment
        implements LoaderManager.LoaderCallbacks<Cursor>, RestaurantListView {

    @BindView(R.id.tv_total_restaurant_count)
    TextView tvTotalRestaurantCount;

    @BindView(R.id.rv_restaurants)
    RecyclerView rvRestaurants;

    private RestaurantListPresenter presenter;

    public static RestaurantListMvpFragment newInstance() {
        RestaurantListMvpFragment fragment = new RestaurantListMvpFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new RestaurantListPresenter(this);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_list_mvp, container, false);
        ButterKnife.bind(this, rootView);

        getActivity().setTitle(getString(R.string.title_activity_main) + " - MVP");

        //From MVP
        presenter.setRestaurantListFromModel();
        displayRestaurantCount();
        rvRestaurants = presenter.getRestaurantAdapter(rvRestaurants, getContext());

        int gridColumnSpanCount = 1;
        rvRestaurants.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DataEvent.RestaurantLoadedEvent event) {

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
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return presenter.onCreateLoader(getContext(), id, args);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        presenter.onLoadFinished(loader, data);
        displayRestaurantCount();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        presenter.onLoaderReset(loader);
    }

    /**
     * For MVP
     */
    @Override
    public void displayRestaurantCount() {
        tvTotalRestaurantCount.setText(presenter.getRestaurantListSize() + " restaurants deliver to you");
    }
}
