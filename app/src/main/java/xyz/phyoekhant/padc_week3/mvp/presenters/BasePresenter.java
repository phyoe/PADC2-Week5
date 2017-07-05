package xyz.phyoekhant.padc_week3.mvp.presenters;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Phyoe Khant on 7/4/17.
 */
public abstract class BasePresenter {

    public void onCreate() {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    public abstract void onStart();

    public abstract void onStop();

    public void onDestroy() {
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }
}
