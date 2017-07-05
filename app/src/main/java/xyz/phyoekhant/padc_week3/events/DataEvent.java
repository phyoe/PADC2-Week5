package xyz.phyoekhant.padc_week3.events;

import java.util.List;

import xyz.phyoekhant.padc_week3.data.vos.RestaurantVO;

/**
 * Created by Phyoe Khant on 6/20/2017.
 */
public class DataEvent {
    public static class RestaurantLoadedEvent {

        private List<RestaurantVO> restaurantList;

        public RestaurantLoadedEvent(List<RestaurantVO> restaurantList) {
            this.restaurantList = restaurantList;
        }

        public List<RestaurantVO> getRestaurantList() {
            return restaurantList;
        }
    }
}
