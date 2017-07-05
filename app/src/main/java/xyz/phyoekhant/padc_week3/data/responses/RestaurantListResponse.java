package xyz.phyoekhant.padc_week3.data.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import xyz.phyoekhant.padc_week3.data.vos.RestaurantVO;

/**
 * Created by Phyoe Khant on 6/19/2017.
 */
public class RestaurantListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("timestamp")
    private String timestamp; //2017-06-19 04:54:52pm

    @SerializedName("restaurants")
    private ArrayList<RestaurantVO> restaurantList;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setRestaurantList(ArrayList<RestaurantVO> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ArrayList<RestaurantVO> getRestaurantList() {
        return restaurantList;
    }
}
