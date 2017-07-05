package xyz.phyoekhant.padc_week3.holders;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.phyoekhant.padc_week3.MyApp;
import xyz.phyoekhant.padc_week3.R;
import xyz.phyoekhant.padc_week3.data.vos.RestaurantVO;
import xyz.phyoekhant.padc_week3.utils.RestaurantsExtraData;

/**
 * Created by Phyoe Khant on 6/20/17.
 */
public class RestaurantViewHolder extends BaseViewHolder<RestaurantVO> {
    @BindView(R.id.iv_ad)
    ImageView imageAdv;

    @BindView(R.id.iv_new)
    ImageView imageNew;

    @BindView(R.id.iv_restaurant)
    ImageView imageRestaurant;

    @BindView(R.id.tv_restaurant_title)
    TextView tvRestaurantTitle;

    @BindView(R.id.tv_tags)
    TextView tvTags;

    @BindView(R.id.tv_total_rating_count)
    TextView tvTotalRatingCount;

    @BindView(R.id.tv_lead_time_in_min)
    TextView tvLeadTimeInMin;

    @BindView(R.id.tv_pricey1)
    TextView tvPricey1;

    @BindView(R.id.tv_pricey2)
    TextView tvPricey2;

    @BindView(R.id.tv_pricey3)
    TextView tvPricey3;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.ic_1)
    ImageView ic_1;

    @BindView(R.id.ic_2)
    ImageView ic_2;

    @BindView(R.id.ic_3)
    ImageView ic_3;

    @BindView(R.id.hidden_sort)
    TextView hiddenSort;

    private RestaurantVO mRestaurant;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        hiddenSort.setVisibility(View.INVISIBLE);
    }

    public void bind(RestaurantVO restaurant) {
        mRestaurant = restaurant;

        //GET KEY
        String key = getKey(restaurant);

        //Ad Image Show
        showAdImage(restaurant);

        //New Icon Show
        showNewIcon(restaurant);

        //Tags
        showTags(restaurant);

        //bind data from RestaurantsExtraData
        RestaurantsExtraData extraData = new RestaurantsExtraData();
        showIcons(extraData, key); //Show Icons
        showPricey(extraData, key); //Pricey

        int sort = extraData.getSort(key);//Set Hidden Sort
        //Log.d(MyApp.TAG, "bind: sort " + sort);
        hiddenSort.setText(sort + "");

        //addrShort
        String addrShort = (restaurant.getAddrShort() == null) ? "" : " (" + restaurant.getAddrShort() + ")";
        tvRestaurantTitle.setText(restaurant.getTitle() + addrShort);

        //Others
        ratingBar.setRating(Float.parseFloat(restaurant.getAverageRatingValue() + ""));//Rating Bar
        tvTotalRatingCount.setText("(" + restaurant.getTotalRatingCount() + ")");
        tvLeadTimeInMin.setText(restaurant.getLeadTimeInMin() + " min.");

        //Restaurant Images
        showRestaurantImage(extraData, key);
    }

    private String getKey(RestaurantVO restaurant) {
        String key = restaurant.getTitle().toLowerCase()
                .replace(" ", "")
                .replace("'", "")
                .replace("&", "");

        //Log.d(MyApp.TAG, "bind: key " + key);

        return key;
    }

    private void showAdImage(RestaurantVO restaurant) {
        boolean is_ad = restaurant.isAd();
        //Log.d(MyApp.TAG, "bind: is_ad " + key + " " + is_ad);
        if (is_ad)
            imageAdv.setVisibility(View.VISIBLE);
        else
            imageAdv.setVisibility(View.INVISIBLE);
    }

    private void showNewIcon(RestaurantVO restaurant) {
        boolean is_new = restaurant.isNew();
        //Log.d(MyApp.TAG, "bind: is_new " + key + " " + is_new);
        if (is_new)
            imageNew.setVisibility(View.VISIBLE);
        else
            imageNew.setVisibility(View.INVISIBLE);
    }

    private void showTags(RestaurantVO restaurant) {
        int size = restaurant.getTags().length;
        String tags = "";
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (i < size - 1)
                    tags += restaurant.getTags()[i].toString() + ", ";
                else
                    tags += restaurant.getTags()[i].toString();
            }
        }
        tvTags.setText(tags);
    }

    private void showPricey(RestaurantsExtraData extraData, String key) {

        int pricey = extraData.getPricey(key);

        if (pricey == 1) {
            tvPricey1.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.divider));
            tvPricey2.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.soft_gray));
            tvPricey3.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.soft_gray));

        } else if (pricey == 2) {
            tvPricey1.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.divider));
            tvPricey2.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.divider));
            tvPricey3.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.soft_gray));

        } else if (pricey == 3) {
            tvPricey1.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.divider));
            tvPricey2.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.divider));
            tvPricey3.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.divider));

        } else {
            tvPricey1.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.soft_gray));
            tvPricey2.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.soft_gray));
            tvPricey3.setTextColor(ContextCompat.getColor(MyApp.getContext(), R.color.soft_gray));
        }
    }

    private void showIcons(RestaurantsExtraData extraData, String key) {

        String[] icons = extraData.getIcons(key);

        int length = icons.length;
        //Log.d(MyApp.TAG, "showIcons: " + length);

        ic_1.setVisibility(View.VISIBLE);
        ic_2.setVisibility(View.VISIBLE);
        ic_3.setVisibility(View.VISIBLE);

        if (length == 1) {

            if (icons[0] == "percent")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_percent_small));
            else if (icons[0] == "halal")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_halal_small));
            else if (icons[0] == "file")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_file_small));

            ic_2.setVisibility(View.GONE);
            ic_3.setVisibility(View.GONE);

        } else if (length == 2) {

            if (icons[0] == "percent")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_percent_small));
            else if (icons[0] == "halal")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_halal_small));
            else if (icons[0] == "file")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_file_small));

            if (icons[1] == "percent")
                ic_2.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_percent_small));
            else if (icons[1] == "halal")
                ic_2.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_halal_small));
            else if (icons[1] == "file")
                ic_2.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_file_small));

            ic_3.setVisibility(View.GONE);

        } else if (length == 3) {

            if (icons[0] == "percent")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_percent_small));
            else if (icons[0] == "halal")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_halal_small));
            else if (icons[0] == "file")
                ic_1.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_file_small));

            if (icons[1] == "percent")
                ic_2.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_percent_small));
            else if (icons[1] == "halal")
                ic_2.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_halal_small));
            else if (icons[1] == "file")
                ic_2.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_file_small));

            if (icons[2] == "percent")
                ic_3.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_percent_small));
            else if (icons[2] == "halal")
                ic_3.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_halal_small));
            else if (icons[2] == "file")
                ic_3.setImageDrawable(ContextCompat.getDrawable(MyApp.getContext(), R.drawable.ic_file_small));
        }
    }

    private void showRestaurantImage(RestaurantsExtraData extraData, String key) {

        int ic_image_restaurant = (extraData.getImage(key) == 0) ? R.drawable.ic_image_24dp : extraData.getImage(key);
        Glide.with(imageRestaurant.getContext())
                .load(ic_image_restaurant) //imageUrl
                .centerCrop()
                .placeholder(R.drawable.ic_image_24dp)
                .error(R.drawable.ic_image_24dp)
                .into(imageRestaurant);

    }
}

