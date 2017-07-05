package xyz.phyoekhant.padc_week3;

import android.app.Application;
import android.content.Context;

import xyz.phyoekhant.padc_week3.models.RestaurantModel;

/**
 * Created by Phyoe Khant on 6/19/2017.
 */
public class MyApp extends Application{

    public static final String TAG = "MyApp";

    private static Context context;  //DON'T

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        RestaurantModel.getInstance().loadRestaurants();
    }

    public static Context getContext(){
        return context;
    }
}
