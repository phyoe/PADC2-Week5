package xyz.phyoekhant.padc_week3.data.agents;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.phyoekhant.padc_week3.data.responses.RestaurantListResponse;
import xyz.phyoekhant.padc_week3.utils.RestaurantsConstants;

/**
 * Created by Phyoe Khant on 6/19/2017.
 */
public interface RestaurantApi {

    @GET(RestaurantsConstants.API_GET_RESTAURANT_LIST)
    Call<RestaurantListResponse> loadRestaurants();
}
