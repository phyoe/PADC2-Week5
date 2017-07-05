package xyz.phyoekhant.padc_week3.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.phyoekhant.padc_week3.data.agents.RestaurantDataAgent;
import xyz.phyoekhant.padc_week3.data.agents.RetrofitDataAgent;

/**
 * Created by Phyoe Khant on 6/20/2017.
 */
public class BaseModel {

    protected RestaurantDataAgent dataAgent;

    public BaseModel() {
        dataAgent = RetrofitDataAgent.getInstance();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj) {

    }
}
