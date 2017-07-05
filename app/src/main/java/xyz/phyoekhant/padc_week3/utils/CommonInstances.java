package xyz.phyoekhant.padc_week3.utils;

import com.google.gson.Gson;

/**
 * Created by Phyoe Khant on 6/19/2017.
 */
public class CommonInstances {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}
