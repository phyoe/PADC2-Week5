package xyz.phyoekhant.padc_week3.fragments;

import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Phyoe Khant on 6/28/17.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();

        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }
}