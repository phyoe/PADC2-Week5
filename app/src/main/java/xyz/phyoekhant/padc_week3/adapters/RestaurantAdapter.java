package xyz.phyoekhant.padc_week3.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.phyoekhant.padc_week3.R;
import xyz.phyoekhant.padc_week3.data.vos.RestaurantVO;
import xyz.phyoekhant.padc_week3.holders.RestaurantViewHolder;

/**
 * Created by Phyoe Khant on 6/20/17.
 */
public class RestaurantAdapter extends BaseRecyclerAdapter<RestaurantViewHolder, RestaurantVO>{

    public RestaurantAdapter(Context context) {
        super(context);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
