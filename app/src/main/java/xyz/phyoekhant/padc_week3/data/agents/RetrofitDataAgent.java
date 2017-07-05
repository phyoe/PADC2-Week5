package xyz.phyoekhant.padc_week3.data.agents;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.phyoekhant.padc_week3.data.responses.RestaurantListResponse;
import xyz.phyoekhant.padc_week3.events.DataEvent;
import xyz.phyoekhant.padc_week3.utils.CommonInstances;
import xyz.phyoekhant.padc_week3.utils.RestaurantsConstants;

/**
 * Created by Phyoe Khant on 6/19/2017.
 */
public class RetrofitDataAgent implements RestaurantDataAgent {

    private static RetrofitDataAgent objInstance;

    private final RestaurantApi theApi;

    public RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantsConstants.RESTAURANT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(RestaurantApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadRestaurants() {
        Call<RestaurantListResponse> loadRestaurantCall = theApi.loadRestaurants();
        loadRestaurantCall.enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {
                if (response.isSuccessful()) {
                    RestaurantListResponse restaurantListResponse = response.body();
                    if (restaurantListResponse == null) {
                        //RestaurantModel.getInstance().notifyErrorInLoadingRestaurants(response.message());
                    } else {
                        //RestaurantModel.getInstance().notifyRestaurantsLoaded(restaurantListResponse.getRestaurantList());
                        EventBus.getDefault().post(new DataEvent.RestaurantLoadedEvent(restaurantListResponse.getRestaurantList()));
                    }
                } else {
                    Log.e("DataAgent", "Loading RestaurantList Not Success");
                }
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable throwable) {
                Log.e("DataAgent", "Loading RestaurantList Failure : " + throwable.getMessage());
                throwable.printStackTrace();
                //RestaurantModel.getInstance().notifyErrorInLoadingRestaurants(throwable.getMessage());
            }
        });

    }
}
